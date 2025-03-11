import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.Repository.CurrentState
import com.example.myapplication.Repository.CurrentState.loading
import com.example.myapplication.Repository.CurrentState.normal
import com.example.myapplication.Repository.CurrentState.successful
import com.example.myapplication.ui.theme.backgroundc
import com.example.myapplication.ui.theme.buttonc
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
    val weatherData by viewModel.weatherdata.observeAsState()
    var progressIndicator by remember { mutableStateOf(false) }
    val state by viewModel.state.observeAsState()


    Column(
        modifier = Modifier.run {
            fillMaxSize()
                .background(backgroundc)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.run { height(64.dp) })
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(4.dp),
            border = BorderStroke(2.dp, buttonc2)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Enter Location") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = buttonc2,
                        unfocusedTextColor = buttonc2,
                        focusedLabelColor = buttonc2,
                        unfocusedLabelColor = buttonc2,
                        cursorColor = buttonc2,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
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
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            progressIndicator = true
                            location = location.trim()
                            if (location.isNotEmpty()) {
                                Onclick(location) // ✅ Trigger API call first
                            } else {
                                progressIndicator = false
                                Toast
                                    .makeText(context, "Please Enter Location", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                )
            }
        }
    }
    if (progressIndicator) {
        ProgressIndicator()
    }
    // ✅ Navigate only when weather data is available
    LaunchedEffect(weatherData) {
        weatherData?.let {
            progressIndicator = false
            navController.navigate(Routes.detailScreen)
        }
    }
}

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(70.dp),
            strokeWidth = 10.dp,
            color = buttonc2
        )
    }

}