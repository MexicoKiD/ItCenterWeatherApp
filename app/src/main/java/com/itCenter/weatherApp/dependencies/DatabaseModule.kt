package com.itCenter.weatherApp.dependencies

import android.content.Context
import androidx.room.Room
import com.itCenter.weatherApp.WEATHER_DATABASE
import com.itCenter.weatherApp.data.database.WeatherDao
import com.itCenter.weatherApp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room
            .databaseBuilder(
                appContext,
                WeatherDatabase::class.java,
                WEATHER_DATABASE
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideChannelDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.channelDao()
    }
}