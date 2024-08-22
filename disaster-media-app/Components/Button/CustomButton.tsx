import { StyleSheet, Text, View ,TouchableOpacity} from 'react-native'
import React from 'react'

const CustomButton = ({title}) => {
  return (
    <TouchableOpacity style={styles.container}>
      <Text style={styles.Button}>{title}</Text>
    </TouchableOpacity>
  )
}

export default CustomButton

const styles = StyleSheet.create({
  container:{
    backgroundColor: '#FF914D',
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    paddingTop: 12,
    paddingBottom: 12,
    marginTop: 10,
    marginBottom: 10,
    borderRadius: 25,
  },
  Button:{
   
  }

})