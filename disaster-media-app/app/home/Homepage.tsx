import {
  View,
  FlatList,
  ActivityIndicator,
  StyleSheet,
  Text,
  TouchableOpacity,
  TextInput,
  Image
} from "react-native";
import React, { useEffect, useState } from "react";
import { SafeAreaView } from "react-native-safe-area-context";

import Card from "@/Components/Card";
import { useNavigation } from "expo-router";

// Define types for the data structure
interface Disaster {
  disasterId: string;
  title: string;
  incidentLocation: string;
  incidentType: string;
  infoPublishedDate: string;
  summary: string;
  videos: string[];
  data: any;
}

const Homepage: React.FC = () => {
  const [disData, setDisData] = useState<Disaster[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [searchItem, setSearchItem] = useState<string | null>("");

  const navigation = useNavigation();
  const getAPIData = async () => {
    try {
      const apiResp = await fetch(
        "https://api.disaster-media.easc01.com/disaster/all?searchTag=&type=&location=&publishedBefore&publishedAfter "
      );
      if (!apiResp.ok) {
        throw new Error("Unable to get data from API");
      }
      const data = await apiResp.json();
      setDisData(data.data); // Adjust this based on the correct structure of your API response
    } catch (error: any) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getAPIData();
  }, []);

  if (loading) {
    return <ActivityIndicator size="large" color="#0000ff" />;
  }

  if (error) {
    return <Text>Error: {error}</Text>;
  }

  if (!disData || disData.length === 0) {
    return <Text>No data available</Text>;
  }

  const filterItem = (e) => {
    if (searchItem === "") {
      return (
        <Card
          data={e}
          onPress={() =>
            navigation.navigate("VideoPage", {
              videosArr: e.videos,
              disasterSummary: e.summary,
            })
          }
        />
      );
    } if (e.title.toLowerCase().includes(searchItem?.toLowerCase())){
      return (
        <Card
          data={e}
          onPress={() =>
            navigation.navigate("VideoPage", {
              videosArr: e.videos,
              disasterSummary: e.summary,
            })
          }
        />
      )
    }
  };

  return (
    <SafeAreaView style={{ flex: 1, padding: 20, backgroundColor: "#F1F1F1" }}>
      <View >
        <TextInput
          placeholder="search.."
          onChangeText={(text) => setSearchItem(text)}
          style={styles.input}
        />
      </View>
      <FlatList
        data={disData}
        keyExtractor={(item) => item.disasterId.toString()}
        renderItem={({ item }) => filterItem(item)}
      />
    </SafeAreaView>
  );
};

export default Homepage;

const styles = StyleSheet.create({
     
  input:{
    height: 50,
    paddingHorizontal: 15,
    backgroundColor: 'white',
    borderRadius: 30,
  },

});
