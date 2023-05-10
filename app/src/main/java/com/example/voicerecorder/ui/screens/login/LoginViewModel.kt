package com.example.voicerecorder.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicerecorder.utils.validation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var userNameErrorData = MutableStateFlow<Validation>(Invalid(""))
    var userPasswordErrorData = MutableStateFlow<Validation>(Invalid(""))
    val loginUserStateData: MutableStateFlow<LoginUserUi> = MutableStateFlow(getEmptyLoginUserUi())

    var loginButton: Flow<Boolean> =
        combine(
            userNameErrorData,
            userPasswordErrorData
        ) { userName, userPassword ->
            return@combine userName is Valid && (userPassword is Valid)
        }

    private fun getEmptyLoginUserUi(): LoginUserUi = LoginUserUi(
        name = "",
        password = ""
    )

    fun validateUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            loginUserStateData.collect { loginUserUi ->
                userNameErrorData.emit(
                    UsernameValidation().isUsernameValid(text = loginUserUi.name)
                )
            }
        }
    }

    fun validatePassword() {
        viewModelScope.launch(Dispatchers.IO) {
            loginUserStateData.collect { loginUserUi ->
                userPasswordErrorData.emit(
                    PasswordValidation().isPasswordValid(text = loginUserUi.password)
                )
            }
        }
    }

    fun setUserName(name: String) {
        loginUserStateData.tryEmit(
            loginUserStateData.value.copy(
                name = name
            )
        )
    }

    fun setUserPassword(password: String) {
        loginUserStateData.tryEmit(
            loginUserStateData.value.copy(
                password = password
            )
        )
    }
}