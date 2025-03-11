package com.example.myapplication.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Api.ApiInterface
import com.example.myapplication.Model.Weather
import com.example.myapplication.Repository.CurrentState.error
import com.example.myapplication.Repository.CurrentState.loading
import com.example.myapplication.Repository.CurrentState.normal
import com.example.myapplication.Repository.CurrentState.successful
import retrofit2.Response

class WeatherRepository(private val apiInterface: ApiInterface) {

    private val _weatherData = MutableLiveData<Weather?>()
    val weatherData: LiveData<Weather?> get() = _weatherData
    private val _state=MutableLiveData<CurrentState>(normal)
    val state: MutableLiveData<CurrentState> get() = _state


    suspend fun getDataFromRepository(location: String): Weather? {

        return try {
                  _state.postValue(loading)
            val result: Response<Weather> = apiInterface.getWeather(location = location)
            Log.d("API_CALL", "Response Code: ${result.code()}, Body: ${result.body()}")

            if (result.isSuccessful && result.body() != null) {
                _state.postValue(successful)
                val weather = result.body()
                _weatherData.postValue(weather)
                weather
            } else {
                _state.postValue(error)
                Log.e("API_ERROR", "Error: ${result.errorBody()?.string()}")
                _weatherData.postValue(null)
                null
            }
        } catch (e: Exception) {
            _state.postValue(error)
            Log.e("API_EXCEPTION", "Exception: ${e.message}")
            _weatherData.postValue(null)
            null
        }
    }

    fun setnull() {
        _weatherData.postValue(null)
    }
    fun setnormal(){
        _state.postValue(normal)
    }
}
