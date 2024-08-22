import { SafeAreaView, StyleSheet, Text, View } from 'react-native'
import React, { useEffect } from 'react'
import Homepage from './home/Homepage'

const Index = () => {
  

  return (
    <SafeAreaView style={styles.container}>
     <Homepage/> 
    </SafeAreaView>
  )
}

export default Index

const styles = StyleSheet.create({
  container:{
    flex :1 ,
    marginTop: 50,
    borderColor:'black',

  },
  header:{
    fontSize: 20,
    color: 'blue',
  }


})