package com.itCenter.weatherApp.data.request

import com.google.gson.annotations.SerializedName
import com.itCenter.weatherApp.*
import com.itCenter.weatherApp.data.dto.WeatherDTO
import org.threeten.bp.LocalDate

data class WeatherResponse(
    @SerializedName(LOCATION) val location: Location,
    @SerializedName(FORECAST) val forecast: Forecast
) {
    fun toWeatherDTO() = WeatherDTO(
        cityName = location.name,
        date = LocalDate.parse(forecast.forecastday[0].date),
        temperatureInCelsius = forecast.forecastday[0].day.avgtemp_c
    )
}

data class Day(
    @SerializedName(AVG_TEMP_C) val avgtemp_c: Double,
    @SerializedName(AVG_TEMP_F) val avgtemp_f: Double
)

data class Location(
    @SerializedName(NAME) val name: String,
    @SerializedName(REGION) val region: String,
    @SerializedName(COUNTRY) val country: String,
    @SerializedName(LOCAL_TIME) val localtime: String
)

data class ForecastDay(
    @SerializedName(DATE) val date: String,
    @SerializedName(DATE_EPOCH) val date_epoch: Int,
    @SerializedName(DAY) val day: Day
)

data class Forecast(
    @SerializedName(FORECAST_DAY) val forecastday: List<ForecastDay>
)