package com.pararam2006.coffeeapp.ui.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.domain.dto.LoginRequestDto
import com.pararam2006.coffeeapp.domain.dto.LoginResponseDto
import com.pararam2006.coffeeapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    val loginUseCase: LoginUseCase
) : ViewModel() {
    var uiState by mutableStateOf(LoginScreenUiState())
        private set

    var loginResult by mutableStateOf<Result<LoginResponseDto>?>(null)
        private set

    fun changeEmail(newText: String) {
        uiState = uiState.copy(
            email = newText
        )
    }

    fun changePassword(newText: String) {
        uiState = uiState.copy(
            password = newText
        )
    }

    fun auth() {
        viewModelScope.launch {
            loginResult = try {
                Result.success(
                    loginUseCase(
                        LoginRequestDto(
                            login = uiState.email,
                            password = uiState.password
                        )
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
            finally {
                Log.d(
                    "RegistratoinScreenViewModel", "result: ${loginResult.toString()}, " +
                            "request: ${LoginRequestDto(login = uiState.email, password = uiState.password)}"
                )
            }
        }
    }
}