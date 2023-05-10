package com.example.voicerecorder.utils.validation

class PasswordValidation {

    fun isPasswordValid(text: String): Validation {
        return when {
            text.isNullOrEmpty() -> Invalid("Password is empty")
            else -> Valid
        }
    }
}