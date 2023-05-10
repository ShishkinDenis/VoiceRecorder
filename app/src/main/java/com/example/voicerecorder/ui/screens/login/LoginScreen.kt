package com.example.voicerecorder.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.voicerecorder.R
import com.example.voicerecorder.navigation.Screen
import com.example.voicerecorder.utils.validation.Invalid
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginViewModel = koinViewModel()
) {
    val userNameErrorData = viewModel.userNameErrorData.collectAsStateWithLifecycle()
    val userPasswordErrorData = viewModel.userPasswordErrorData.collectAsStateWithLifecycle()
    val loginUserUi = viewModel.loginUserStateData.collectAsStateWithLifecycle()
    val loginButtonIsEnabled = viewModel.loginButton.collectAsStateWithLifecycle(false)

    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        UsernameField(
            username = loginUserUi.value.name,
            onUsernameChange = {
                viewModel.setUserName(it)
                viewModel.validateUserName()
            },
            isError = userNameErrorData.value is Invalid,
            errorMessage = (userNameErrorData.value as? Invalid)?.errorMessage ?: ""
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(
            password = loginUserUi.value.password,
            onPasswordChange = {
                viewModel.setUserPassword(it)
                viewModel.validatePassword()
            },
            isError = userPasswordErrorData.value is Invalid,
            errorMessage = (userPasswordErrorData.value as? Invalid)?.errorMessage ?: ""
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = {
                navController.navigate(Screen.Recording.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            },
            enabled = loginButtonIsEnabled.value,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(R.string.login), fontSize = 22.sp)
        }
    }
}


