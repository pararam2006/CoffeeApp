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
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    val loginUseCase: LoginUseCase,
    private val tokenRepository: TokenRepository
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
            val result = try {
                val response = loginUseCase(
                    LoginRequestDto(
                        login = uiState.email,
                        password = uiState.password
                    )
                )
                tokenRepository.saveTokenToPrefs(response.token)
                uiState = uiState.copy(isSuccess = true)
                Result.success(response)
            } catch (e: Exception) {
                uiState = uiState.copy(isSuccess = false)
                Result.failure(e)
            }
            loginResult = result
            Log.d(
                "RegistratoinScreenViewModel", "result: $result, " +
                        "request: ${LoginRequestDto(login = uiState.email, password = uiState.password)}"
            )
        }
    }
}