package com.itCenter.weatherApp.data.dto

import com.google.gson.annotations.SerializedName
import com.itCenter.weatherApp.data.database.WeatherEntity
import org.threeten.bp.LocalDate

data class WeatherDTO(
    @SerializedName("cityName")
    val cityName: String,
    @SerializedName("date")
    val date: LocalDate,
    @SerializedName("temperatureInCelsius")
    val temperatureInCelsius: Double
) {
    fun toWeatherEntity() = WeatherEntity(
        id = null,
        location = cityName.uppercase(),
        date = date.toString(),
        temperatureInCelsius = temperatureInCelsius
    )
}