package com.example.myapplication

import HomeScreen
import WeatherViewModel
import WeatherViewModelFactory
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Api.ApiInterface
import com.example.myapplication.Api.ApiObject
import com.example.myapplication.Repository.WeatherRepository

import com.example.myapplication.View.WeatherDetail
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.Routes
import kotlinx.coroutines.launch
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WeatherViewModel  // Declare it at the top

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiInterface = ApiObject.getInstance().create(ApiInterface::class.java)
        val repository = WeatherRepository(apiInterface)
        viewModel = ViewModelProvider(
            this, WeatherViewModelFactory(repository)
        )[WeatherViewModel::class.java] // Use bracket syntax for better compatibility

        setContent {
            MyApplicationTheme {
                NavigationArea(viewModel, this)
            }
        }
    }
}



@Composable
fun NavigationArea(viewModel: WeatherViewModel, context: Context) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.homeScreen) {
        composable(route = Routes.homeScreen) {
            HomeScreen(navController, context, viewModel) {
                viewModel.viewModelScope.launch {
                    viewModel.getData(it)
                }
            }
        }
        composable(route = Routes.detailScreen) {
            WeatherDetail(navController, viewModel)
        }
    }
}