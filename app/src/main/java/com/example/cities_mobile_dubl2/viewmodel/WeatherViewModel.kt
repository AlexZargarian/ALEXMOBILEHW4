package com.example.cities_mobile_dubl2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cities_mobile_dubl2.constants.API_KEY
import com.example.cities_mobile_dubl2.data.cities
import com.example.cities_mobile_dubl2.model.WeatherData
import com.example.cities_mobile_dubl2.network.RetrofitClient
import com.example.cities_mobile_dubl2.network.WeatherService
import kotlinx.coroutines.launch
import retrofit2.Response



enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT
}
class WeatherViewModel : ViewModel() {
    private val weatherService = RetrofitClient.weatherService

    private val _weatherData = MutableLiveData<List<WeatherData>>()
    val weatherData: LiveData<List<WeatherData>> get() = _weatherData

    private val _location = MutableLiveData<Pair<Double, Double>>()
    val location: LiveData<Pair<Double, Double>> get() = _location

    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double> get() = _latitude

    private val _longitude = MutableLiveData<Double>()
    val longitude: LiveData<Double> get() = _longitude

    private val _temperatureUnit = MutableLiveData(TemperatureUnit.CELSIUS)
    val temperatureUnit: LiveData<TemperatureUnit> get() = _temperatureUnit

    init {
        viewModelScope.launch {
            try {
                val weatherList = mutableListOf<WeatherData>()
                for (city in cities) {
                    val response = weatherService.getWeather(city.name, API_KEY, "en")
                    response.let {
                        it.body()?.let { it1 -> weatherList.add(it1) }
                    }
                }
                _weatherData.value = weatherList
            } catch (e: Exception) {
                Log.e("Retrofit", "Error fetching weather data", e)
            }
        }
    }

    fun setTemperatureUnit(unit: TemperatureUnit) {
        _temperatureUnit.value = unit
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                val response: Response<WeatherData> = WeatherService.instance.getWeather(city, API_KEY, "en")
                if (response.isSuccessful) {
                    _weatherData.value = listOf(response.body()!!)
                } else {
                    Log.e("Retrofit", "Failed to retrieve weather data for $city")
                }
            } catch (e: Exception) {
                Log.e("Retrofit", "Error fetching weather data", e)
            }
        }
    }

    fun setLocation(latitude: Double, longitude: Double) {
        _latitude.value = latitude
        _longitude.value = longitude
        _location.value = Pair(latitude, longitude)
    }
}
