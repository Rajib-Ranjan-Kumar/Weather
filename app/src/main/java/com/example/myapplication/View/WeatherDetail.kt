package com.example.myapplication.View

import WeatherViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R

import com.example.myapplication.ui.theme.backgroundc
import com.example.myapplication.ui.theme.buttonc2
import com.example.myapplication.util.Routes

@Composable
fun WeatherDetail(navController: NavHostController, viewModel: WeatherViewModel) {
    val value by remember { mutableStateOf( viewModel.weatherdata.value)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundc)
            .padding(18.dp, 55.dp, 8.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {navController.navigate(route = Routes.homeScreen){
                        popUpTo(0){inclusive=true}
                    } },
                colorFilter = ColorFilter.tint(
                    buttonc2
                )
            )
        }
        if (value != null) {
            Text(
                "${value!!.location.name},${value!!.location.country}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = buttonc2,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(Modifier.height(100.dp))
        if (value != null) {
            Text(
                "${value!!.current.temp_c}°C",
                fontSize = 100.sp,
                color = buttonc2,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
            )
        }
        if (value != null) {
            Text("${value!!.current.condition.text}", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = buttonc2)
        }
        Spacer(modifier=Modifier.height(100.dp))
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Wind", fontSize = 25.sp, color = buttonc2)
                if (value != null) {
                    Text("${value!!.current.wind_kph}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = buttonc2)
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Humidity", fontSize = 25.sp, color = buttonc2)
                if (value != null) {
                    Text("${value!!.current.humidity}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = buttonc2)
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Pressure", fontSize = 25.sp, color = buttonc2)
                if (value != null) {
                    Text("${value!!.current.pressure_mb}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = buttonc2)
                }
            }


        }

    }

}