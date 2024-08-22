import * as React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import VideoPage from "./VideoPages"; // Import your VideoPage component
import Homepage from "./home/Homepage";
import { View } from "react-native";

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer independent={true}>
      <Stack.Navigator initialRouteName="Homepage" screenOptions={{
        headerStyle:{
          backgroundColor: "#FF5757"
        }
      }}>
        <Stack.Screen
          name="Homepage"
          component={Homepage}
          options={{ headerShown: false }}
        />
        <Stack.Screen
          name="VideoPage"
          component={VideoPage}
          options={{ 
            title: "Disaster",
           }}

        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
