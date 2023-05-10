package com.example.voicerecorder.utils.validation

class UsernameValidation {

    fun isUsernameValid(text: String): Validation {
        return when {
            text.isNullOrEmpty() -> Invalid("Username is empty")
            else -> Valid
        }
    }
}