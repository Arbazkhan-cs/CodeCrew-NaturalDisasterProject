import {
  Button,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import React, { useCallback, useState } from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import VideoCard from "../../Components/VideoCard";

interface VideoProps {
  title: string;
  description: string;
  date: string;
}

type RootStackParamList = {
  VideoPage: { videos: string[]; disasterSummary: string };
};
type Props = NativeStackScreenProps<RootStackParamList, "VideoPage">;

const VideoPage: React.FC<VideoProps> = ({ route }) => {
  const [playing, setPlaying] = useState(false);
  const [expandedIndex, setExpandedIndex] = useState<number | null>(null);

  const onStateChange = useCallback((state) => {
    if (state === "ended") {
      setPlaying(false);
    }
  }, []);
  const togglePlaying = useCallback(() => {
    setPlaying((prev) => !prev);
  }, []);

  // Stripping URL
  const getYoutubeVideoID = (url: string): string | null => {
    const urlParams = new URLSearchParams(new URL(url).search);
    return urlParams.get("v");
  };

  const toggleDescription = (index: number) => {
    setExpandedIndex(expandedIndex === index ? null : index);
  };

  const { videosArr, disasterSummary } = route.params;

  // main return point
  return (
    <ScrollView style={styles.container}>
      <Text style={styles.description}>{disasterSummary}</Text>
      <View style={styles.videoContainer}>
        <Text style={styles.videoTitle}>Videos</Text>
      </View>
      <View>
        {videosArr.map((video, index) => (
          <VideoCard key={index} title={video.title}/>
        ))}
      </View>
    </ScrollView>
  );
};

export default VideoPage;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    backgroundColor: "#F1F1F1",
  },
  description: {
    backgroundColor: "white",
    padding: 16,
    fontSize: 15,
    borderRadius: 10,
  },
  videoContainer: {
    backgroundColor: "#FF914D",
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    paddingTop: 12,
    paddingBottom: 12,
    marginTop: 10,
    marginBottom: 10,
    borderRadius: 25,
  },
  videoTitle: {
    color: "white",
    fontSize: 16,
  },
});
