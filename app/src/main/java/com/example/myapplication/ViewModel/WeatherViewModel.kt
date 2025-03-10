import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Weather
import com.example.myapplication.Repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getDataFromRepository("London")
        }
    }

    suspend fun getData(Location: String) {
         weatherRepository.getDataFromRepository(Location)
    }

fun iscontain():Boolean{
    return weatherRepository.weatherData!=null
}

    val weatherdata :LiveData<Weather?>
        get()=weatherRepository.weatherData
}
