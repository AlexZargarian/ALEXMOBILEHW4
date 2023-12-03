package com.example.cities_mobile_dubl2.view

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cities_mobile_dubl2.model.WeatherData
import com.example.cities_mobile_dubl2.viewmodel.TemperatureUnit


@Composable
fun DisplayTemperature(weather: WeatherData?, temperatureUnit: TemperatureUnit?) {
    val temperatureCelsius = weather?.current?.temp_c
    val temperatureFahrenheit = temperatureCelsius?.let { (it * 9 / 5) + 32 }

    val temperature = when (temperatureUnit) {
        TemperatureUnit.CELSIUS -> temperatureCelsius
        TemperatureUnit.FAHRENHEIT -> temperatureFahrenheit
        else -> null
    }

    val humidity = weather?.humid?.humidity

    val symbol = when (temperatureUnit) {
        TemperatureUnit.CELSIUS -> "°C"
        TemperatureUnit.FAHRENHEIT -> "°F"
        else -> ""
    }

    val temperatureText = temperature?.let { "$it$symbol" } ?: "N/A"
    val humidityText = if (temperature == null) {
        humidity?.let { "Humidity: $it%" } ?: "N/A"
    } else {
        ""
    }

    Log.d("DisplayTemperature", "Temperature: $temperatureText, $humidityText")

    Text(
        text = "Temperature: $temperatureText, $humidityText",
        style = MaterialTheme.typography.bodyMedium
    )
}
