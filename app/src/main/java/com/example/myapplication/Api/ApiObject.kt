package com.example.myapplication.Api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject {
    private val BASE_URL="https://api.weatherapi.com/v1/"

     fun getInstance():Retrofit{
         return Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }
}