package com.example.myapplication.Model

data class City (
    val location:String="",
    val country:String="",
    val Temperature:Double=0.0,
    val condition:String="",
    val WindSpeed:Double=0.0,
    val humidity:Double=0.0,
    val pressur:Double=0.0
)

val Information = listOf(
    City("Bhubaneswar", "India", 32.5, "Sunny", 12.3, 65.0, 1013.0),
    City("New York", "USA", 18.2, "Cloudy", 9.8, 72.0, 1017.5),
    City("London", "UK", 14.5, "Rainy", 15.2, 80.0, 1015.8),
    City("Tokyo", "Japan", 26.1, "Clear", 10.4, 68.0, 1012.3),
    City("Sydney", "Australia", 22.8, "Windy", 20.5, 60.0, 1009.6)
)
