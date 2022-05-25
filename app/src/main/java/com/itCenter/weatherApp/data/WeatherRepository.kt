package com.itCenter.weatherApp.data

import com.itCenter.weatherApp.data.database.WeatherDao
import com.itCenter.weatherApp.data.database.WeatherEntity
import com.itCenter.weatherApp.data.dto.WeatherDTO
import com.itCenter.weatherApp.data.request.WeatherInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherInterface,
    private val weatherDao: WeatherDao
) {

    fun getWeather(location: String, date: String): Observable<WeatherDTO> = weatherApi
        .getWeatherForCity(location, date)
        .map { it.toWeatherDTO() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun addWeatherRecord(weatherEntity: WeatherEntity): Completable = Completable
        .fromAction {
            weatherDao.insert(weatherEntity)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


    fun getWeatherRecord(location: String, date: String): Single<WeatherEntity> =
        weatherDao.getWeatherByCityAndDate(location, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}