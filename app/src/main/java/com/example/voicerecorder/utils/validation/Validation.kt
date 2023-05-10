package com.example.voicerecorder.utils.validation

sealed class Validation
object Valid : Validation()
data class Invalid(val errorMessage: String) : Validation()