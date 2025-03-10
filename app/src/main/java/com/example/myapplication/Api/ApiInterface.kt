package com.example.myapplication.Api

import com.example.myapplication.Model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("current.jason")
    suspend fun getWeather(
        @Query("key")key:String="783d6f9532a14300bee75935250903",
        @Query("q")location:String,
        @Query("aqi") airquality:String="no"
        ): Response<Weather>
}