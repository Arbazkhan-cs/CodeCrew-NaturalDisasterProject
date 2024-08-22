import { StyleSheet, Text, View } from 'react-native'
import React from 'react'

export default function profile() {
  return (
    <View>
      <Text style={styles.header}>Profile</Text>
    </View>
  )
}

const styles = StyleSheet.create({
    header:{
        fontSize: 20,
    }
})