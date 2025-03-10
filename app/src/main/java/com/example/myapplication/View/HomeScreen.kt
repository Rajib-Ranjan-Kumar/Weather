package com.example.myapplication.View

import WeatherViewModel
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R

import com.example.myapplication.ui.theme.backgroundc
import com.example.myapplication.ui.theme.buttonc2
import com.example.myapplication.util.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context,
    viewModel: WeatherViewModel,
    Onclick: (l: String) -> Unit
) {

    var location by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundc),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(4.dp),
            border = BorderStroke(2.dp, buttonc2)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = location,
                    onValueChange = { location = it },
                    label = { Text("Enter Location") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,  // For entered text color
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White, // Label color when focused
                        unfocusedLabelColor = Color.White, // Label color when not focused
                        cursorColor = Color.White,  // Cursor color
                        focusedBorderColor = Color.Transparent, // Border color when focused
                        unfocusedBorderColor = Color.Transparent // Border color when not focused
                        ,
                        focusedContainerColor = Color.Unspecified
                    ),
                    modifier = Modifier
                        .background(backgroundc)
                        .fillMaxWidth(0.84f)
                        .fillMaxHeight(0.09f),
                    shape = RoundedCornerShape(26),
                    textStyle = TextStyle(fontSize = 25.sp),
                    maxLines = 1
                )
                Image(painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            location = location.trim()
                            if (location.isNotEmpty()) {
                                location = location.replaceFirstChar { it.uppercase() }

                                Onclick(location)

                                if (viewModel.iscontain() && viewModel.weatherdata==null) {

                                    navController.navigate(Routes.detailScreen)
                                }

                            } else {
                                Toast
                                    .makeText(
                                        context, "Pleasue Enter Location", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        })
            }

        }
    }
}





