package com.itCenter.weatherApp

val US_ZIP_CODE_PATTERN = Regex("^\\d{5}(?:[-\\s]\\d{4})?\$")
val UK_ZIP_CODE_PATTERN = Regex("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})")
val CANADA_ZIP_CODE_PATTERN = Regex("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]\$")
val CITY_PATTERN = Regex("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*\$")

const val WEATHER_DATABASE = "weather_database"
const val TABLE_NAME = "weather"
const val KEY = "key"
const val API_KEY = "63865949c706444eb88152338222105"
const val WEATHER_HISTORY_REQUEST_URL = "history.json"
const val QUERY_PARAMETER = "q"
const val DATE_TIME_PARAMETER = "dt"

const val LOCATION = "location"
const val FORECAST = "forecast"
const val AVG_TEMP_C = "avgtemp_c"
const val AVG_TEMP_F = "avgtemp_F"
const val NAME = "name"
const val REGION = "region"
const val COUNTRY = "country"
const val LOCAL_TIME = "localtime"
const val DATE = "date"
const val DATE_EPOCH = "date_epoch"
const val DAY = "day"
const val FORECAST_DAY = "forecastday"

const val RETROFIT = "retrofit"
const val BASE_URL = "https://api.weatherapi.com/v1/"

const val REQUEST_KEY = "request"
const val CITY = "city"
const val INCORRECT_DATA = "Incorrect data was entered"
const val SERVER_ERROR = "Server is not responding"