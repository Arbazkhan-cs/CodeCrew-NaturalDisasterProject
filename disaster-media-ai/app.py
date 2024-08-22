from flask import Flask, request, jsonify
from langchain_groq import ChatGroq
from langchain.agents import tool, create_tool_calling_agent, AgentExecutor
from langchain.prompts import ChatPromptTemplate
from dotenv import load_dotenv
import datetime
import json
import logging

# Load environment variables
load_dotenv()

# Initialize Flask app
app = Flask(__name__)

# Initialize logger
logging.basicConfig(level=logging.INFO)

# Create LLM
llm = ChatGroq(model="llama3-8b-8192", temperature=0.5)

# Prompt Template
promptTemplate = [
    ("system", """
    Task: Summarize news content which is provided by the user related to natural disasters, following these guidelines:
    Check Relevance:
        Determine if the content is related to a natural disaster (e.g., earthquake, flood, hurricane, wildfire, etc.).
        If unrelated, return False.
    If Related:
        Summarize the content concisely and provide key details in minimum of 50 words.
        Structure the summary in the following JSON format:
     <startJson>
        "text": "Main heading summarizing the key point of the news",
        "description": "Detailed summary of the news content",
        "location": "Location of the incident if mentioned, else None",
        "incidentType": "Specific type of natural disaster",
        "recordid": "Location_IncidentType_Date (Add None in place of location if not given and all the alphabets are in lowercase and separated by '_' )"
     <endJson>
    Example:
     <startJson>
        "text": "Storm left hundreds of thousands of Puerto Ricans without power and water in sweltering heat",
        "description": "Hurricane Ernesto barreled toward Bermuda on Thursday after leaving hundreds of thousands of people in Puerto Rico without power or water as sweltering heat enveloped the US territory, raising concerns about people's health.",
        "location": "Puerto Rico",
        "incidentType": "Hurricane",
        "recordid": "puerto-rico_hurricane_2024-01-16"
     <endJson>
    Output: The result should either be False (if unrelated) or a JSON object with the summarized information as specified, without any additional information.
    Note: Do not add your own database information, just summarize the user's description.
    {agent_scratchpad}
    """),
    ("user", "Text: {text}\nDescription: {description}")
]

# Create Prompt
prompt = ChatPromptTemplate.from_messages(promptTemplate)

# Create Agent Tools and Agent
@tool
def get_date() -> str:
    """Return the current date. Use this tool only once if the date is not provided in the description."""
    return str(datetime.datetime.today()).split(" ")[0]

tools = [get_date]
agent = create_tool_calling_agent(llm=llm, tools=tools, prompt=prompt)
agent_executor = AgentExecutor(agent=agent, tools=tools, verbose=True)

# FINAL FUNCTION TO GET RESPONSE FOR EACH ITEM
def process_item(text, description, id_):
    try:
        # Invoke the agent with the provided text and description
        response = agent_executor.invoke({"text": text, "description": description})
        output = dict(response).get('output', '')

        # Logging the raw output for debugging
        logging.info(f"Raw output from LLM: {output}")

        # Extract the JSON part inside <tool-use> tags
        try:
            # Check if <tool-use> tags are present
            if "<tool-use>" in output and "</tool-use>" in output:
                json_str = output.split("<tool-use>")[1].split("</tool-use>")[0].strip()
                output_dict = json.loads(json_str)
            else:
                logging.error("No <tool-use> tags found in the LLM output")
                return {"error": "Invalid LLM output format"}
        except (ValueError, json.JSONDecodeError) as e:
            logging.error(f"Error extracting or decoding JSON: {e}")
            return {"error": "Failed to parse the LLM output as JSON"}

        # Ensure the output is a dictionary
        if not isinstance(output_dict, dict):
            logging.error("LLM output is not in the expected dictionary format")
            return {"error": "LLM output is not in the expected dictionary format"}

        # Add id_ to the beginning of the response JSON
        output_with_id = {"id_": id_}
        output_with_id.update(output_dict)

        return output_with_id

    except Exception as e:
        logging.error(f"An unexpected error occurred: {e}")
        return {"error": "An unexpected error occurred during processing"}

# Define API endpoint to handle multiple items
@app.route('/summarize', methods=['POST'])
def summarize():
    data = request.get_json()
    responses = []

    for item in data:
        text = item.get("text", "")
        description = item.get("description", "")
        id_ = item.get("id", "")

        if not text or not description:
            responses.append({"id": id_, "error": "Text and description are required"})
        else:
            response = process_item(text=text, description=description, id_=id_)
            responses.append(response)

    return jsonify(responses), 200

@app.route('/', methods=['GET'])
def home():
    return "Welcome to my Disasters News Summary API :)"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=7860, threaded=True)
