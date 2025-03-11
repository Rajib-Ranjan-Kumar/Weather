import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.Weather
import com.example.myapplication.Repository.CurrentState
import com.example.myapplication.Repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getDataFromRepository("")
        }
    }

    fun getData(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getDataFromRepository(location)
        }
    }

fun iscontain():Boolean{
    return weatherRepository.weatherData!=null
}
    fun setnull(){
        weatherRepository.setnull()
    }

    fun makenormal(){
        weatherRepository.setnull()
    }

    val weatherdata :LiveData<Weather?>
        get()=weatherRepository.weatherData
    val state:LiveData<CurrentState>get() = weatherRepository.state
}
