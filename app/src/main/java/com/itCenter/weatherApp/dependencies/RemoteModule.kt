package com.itCenter.weatherApp.dependencies

import com.itCenter.weatherApp.RETROFIT
import com.itCenter.weatherApp.data.WeatherRepository
import com.itCenter.weatherApp.data.database.WeatherDao
import com.itCenter.weatherApp.data.request.WeatherInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideWeatherApi(@Named(RETROFIT) retrofit: Retrofit): WeatherInterface {
        return retrofit.create(WeatherInterface::class.java)
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(api: WeatherInterface, locale: WeatherDao): WeatherRepository {
        return WeatherRepository(api, locale)
    }
}