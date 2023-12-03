package com.example.cities_mobile_dubl2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cities_mobile_dubl2.MainActivity
import com.example.cities_mobile_dubl2.R
import com.example.cities_mobile_dubl2.constants.SECOND_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel

@Composable
fun WelcomeScreen(navController: NavController, activity: MainActivity, viewModel: WeatherViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (activity.checkLocationPermission()) {
            val weatherData = viewModel.weatherData.value
            if (weatherData != null) {
                Text(
                    text = "Current Temperature: ${weatherData[0].current.temp_c}°C",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            } else {
                Text(
                    text = "No weather available",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        } else {
            Text(
                text = "No location available",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Display latitude and longitude
        val latitude = viewModel.latitude.value
        val longitude = viewModel.longitude.value

        Text(
            text = "Latitude: $latitude, Longitude: $longitude",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display current temperature unit
        Text(
            text = "Temperature Unit: ${viewModel.temperatureUnit.value?.name}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(SECOND_SCREEN_ROUTE)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = stringResource(id = R.string.explore_cities))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("settings_screen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Settings")
        }
    }
}
