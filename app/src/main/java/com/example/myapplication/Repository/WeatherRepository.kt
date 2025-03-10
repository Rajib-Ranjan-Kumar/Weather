package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Api.ApiInterface
import com.example.myapplication.Model.Weather
import retrofit2.Response

class WeatherRepository(private val apiInterface: ApiInterface) {

    private val _weatherData = MutableLiveData<Weather?>()
    val weatherData: LiveData<Weather?> get() = _weatherData

    suspend fun getDataFromRepository(location: String): Weather? {
        return try {
            val result: Response<Weather> = apiInterface.getWeather(location = location)
            if (result.isSuccessful && result.body() != null) {
                val weather = result.body()

                _weatherData.postValue(weather)
                weather
            } else {
                _weatherData.postValue(null)
                null
            }
        } catch (e: Exception) {
            _weatherData.postValue(null)
            null
        }
    }
}
