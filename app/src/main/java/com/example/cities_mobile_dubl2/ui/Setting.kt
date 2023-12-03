package com.example.cities_mobile_dubl2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cities_mobile_dubl2.viewmodel.TemperatureUnit
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel


@Composable
fun SettingsScreen(navController: NavHostController, viewModel: WeatherViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to set temperature unit to Celsius
        Button(
            onClick = {
                viewModel.setTemperatureUnit(TemperatureUnit.CELSIUS)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp)
        ) {
            Text(text = "Celsius")
        }

        // Button to set temperature unit to Fahrenheit
        Button(
            onClick = {
                viewModel.setTemperatureUnit(TemperatureUnit.FAHRENHEIT)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp)
        ) {
            Text(text = "Fahrenheit")
        }

        // Button to navigate back to the WelcomeScreen
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp)
        ) {
            Text(text = "Back to Welcome")
        }
    }
}
