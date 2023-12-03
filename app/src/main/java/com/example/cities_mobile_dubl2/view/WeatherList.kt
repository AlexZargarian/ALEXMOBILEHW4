package com.example.cities_mobile_dubl2.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cities_mobile_dubl2.data.cities
import com.example.cities_mobile_dubl2.model.City
import com.example.cities_mobile_dubl2.model.WeatherData
import com.example.cities_mobile_dubl2.viewmodel.TemperatureUnit

@Composable
fun WeatherList(weatherList: List<WeatherData>?, temperatureUnit: TemperatureUnit?) {
    if (weatherList != null && temperatureUnit != null) {
        LazyColumn {
            itemsIndexed(weatherList) { index, weather ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CityItem(city = City(name = cities[index].name, description = cities[index].description, imageRes = cities[index].imageRes))
                    DisplayTemperature(weather = weather, temperatureUnit = temperatureUnit)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    } else {
        // Handle the case where either weatherList or temperatureUnit is null
        Text(text = "Weather data not available")
    }
}
