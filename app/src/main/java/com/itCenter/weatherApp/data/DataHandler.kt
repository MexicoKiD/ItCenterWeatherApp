package com.itCenter.weatherApp.data

sealed class DataHandler {
    class Success<T>(var data: T?) : DataHandler()
    object Failure : DataHandler()
}