import {Image, StyleSheet, Text, View } from 'react-native'
import React from 'react'

export default function VideoCard({title}) {
  return (
    <View style={styles.container}>
        <Image
            style={styles.thumbnailContainer} 
            source={{uri:'https://picsum.photos/200/100'}}
        />
        <Text style={styles.videoTitle} numberOfLines={3}>{title}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
    container:{
        flex: 1,
        flexDirection:'row',
        gap: 10 ,
        backgroundColor: "white",
        marginTop: 10,
        alignItems: 'center',
        justifyContent: 'space-between', 
        padding: 10,
        borderRadius: 30,
    },
    thumbnailContainer:{
        height: 100,
        width: 180,
        flex: 1,
        borderRadius: 20,
    },
    videoTitle:{
        fontSize: 16,

        flex: 1
    }
    
})  