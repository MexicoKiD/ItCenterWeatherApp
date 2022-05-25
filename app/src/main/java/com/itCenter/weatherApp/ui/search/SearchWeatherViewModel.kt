package com.itCenter.weatherApp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itCenter.weatherApp.data.DataHandler
import com.itCenter.weatherApp.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _getDataHandlerState = MutableLiveData<DataHandler>()
    val dataHandlerState: LiveData<DataHandler> = _getDataHandlerState

    fun getWeatherForCityAndDate(location: String, date: String) {
        weatherRepository.getWeatherRecord(location, date)
            .subscribe(
                {
                    _getDataHandlerState.value = DataHandler.Success(it.toWeatherDTO())
                },
                {
                    weatherRepository.getWeather(location, date).subscribe({ weatherDto ->
                            weatherRepository.addWeatherRecord(weatherDto.toWeatherEntity())
                                .subscribe(
                                    {
                                        _getDataHandlerState.value = DataHandler.Success(weatherDto)
                                    },
                                    {
                                        _getDataHandlerState.value = DataHandler.Failure
                                    }
                                )
                        },
                        {
                            _getDataHandlerState.value = DataHandler.Failure
                        })
                }
            )
    }


}