import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import React from "react";

interface CardProps {
  data: {
    disasterId: string;
    title: string;
    incidentType: string;
    summary: string;
    videos: string[];
  };
  onPress: () => void;
}

const Card: React.FC<CardProps> = ({ data, onPress }) => {
  const { title, summary, incidentType, disasterId, videos } = data;

  const colorType = (type: string): string => {
    switch (type.toLowerCase()) {
      case "earthquake":
        return "#0CC0DF";
        break;
      case "hurricane":
        return "#FF5757";
        break;
      case "flooding":
        return "#0CC0DF";
        break;
      case "wildfire":
        return "#FF914D";
        break;
      case "tornado":
        return "#FF5757";
        break;
      case "volcanic eruption":
        return "orange";
        break;
      default:
        return "pink";
    }
  };

  const firstWordOnly = (str: string): string => {
    const firstWord = str.split(" ")[0];
    return firstWord.charAt(0).toUpperCase() + firstWord.slice(1).toLowerCase();
  };

  const truncateTitle = (str: string): string => {
    const words = str.split(" ");
    if (words.length > 3) {
      return words.slice(0, 3).join(" ") + "...";
    }
    return str;
  };

  return (
    <View>
      <TouchableOpacity key={disasterId} onPress={onPress}>
        <View style={styles.card1}>
          <View style={styles.cardHeading}>
            <Text style={styles.cardTitle}>{truncateTitle(title)}</Text>

            <Text
              style={[
                styles.cardType,
                { backgroundColor: colorType(incidentType) },
              ]}
            >
              {firstWordOnly(incidentType)}
            </Text>
          </View>
          <Text numberOfLines={3} ellipsizeMode="tail">
            {summary}
          </Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export default Card;

const styles = StyleSheet.create({
  card1: {
    flex: 1,
    flexDirection: "column",
    gap: 6,
    height: 110,
    borderRadius: 10,
    padding: 10,
    marginTop: 15,
    backgroundColor: "white",
  },
  cardHeading: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  cardTitle: {
    fontSize: 17,
    fontWeight: "bold",
  },
  cardType: {
    fontWeight: "bold",
    fontSize: 12,
    padding: 5,
    paddingStart: 10,
    paddingEnd: 10,
    borderRadius: 15,
  },
});
