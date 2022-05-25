package com.itCenter.weatherApp

fun String.cityOrPostalCodeInputIsValid() = when {
    this.isEmpty() -> false
    US_ZIP_CODE_PATTERN.matches(this) || UK_ZIP_CODE_PATTERN.matches(this) || CANADA_ZIP_CODE_PATTERN.matches(
        this
    ) || CITY_PATTERN.matches(this) -> true
    else -> false
}

fun String.trimAndUppercase() = this.trim().uppercase()
