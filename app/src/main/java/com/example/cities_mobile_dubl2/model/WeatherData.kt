package com.example.cities_mobile_dubl2.model

data class WeatherData(
    val location: Location,
    val current: Current,
    val currentF: CurrentF,
    val humid: Humid

)

data class Location(
    val name: String
)

data class Current(
    var temp_c: Float
)

data class CurrentF(
    var temp_f: Float
)


data class Humid(
    var humidity: Float
)

