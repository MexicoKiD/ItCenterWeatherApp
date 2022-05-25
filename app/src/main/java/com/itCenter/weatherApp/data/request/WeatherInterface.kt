package com.itCenter.weatherApp.data.request

import com.itCenter.weatherApp.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    @GET(WEATHER_HISTORY_REQUEST_URL)
    fun getWeatherForCity(
        @Query(QUERY_PARAMETER) city: String,
        @Query(DATE_TIME_PARAMETER) date: String,
        @Query(KEY) apiKey: String = API_KEY
    ): Observable<WeatherResponse>
}