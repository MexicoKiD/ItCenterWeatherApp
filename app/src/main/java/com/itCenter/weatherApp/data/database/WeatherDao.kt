package com.itCenter.weatherApp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(weatherEntity: WeatherEntity): Long

    @Query("SELECT * FROM weather WHERE location = :location AND date = :date")
    fun getWeatherByCityAndDate(location: String, date: String): Single<WeatherEntity>
}