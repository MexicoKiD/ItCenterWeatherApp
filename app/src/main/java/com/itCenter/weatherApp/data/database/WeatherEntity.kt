package com.itCenter.weatherApp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itCenter.weatherApp.TABLE_NAME
import com.itCenter.weatherApp.data.dto.WeatherDTO
import org.threeten.bp.LocalDate
import kotlin.random.Random

@Entity(tableName = TABLE_NAME)
data class WeatherEntity(
    @PrimaryKey
    val id: Int? = Random.nextInt(),
    val location: String,
    val temperatureInCelsius: Double,
    val date: String
) {
    fun toWeatherDTO() = WeatherDTO(
        cityName = location,
        date = LocalDate.parse(date),
        temperatureInCelsius = temperatureInCelsius
    )
}
