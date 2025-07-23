package com.pararam2006.coffeeapp.ui.registration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.domain.dto.RegisterRequestDto
import com.pararam2006.coffeeapp.domain.dto.RegisterResponseDto
import com.pararam2006.coffeeapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class RegistrationScreenViewModel(
    val registerUseCase: RegisterUseCase
) : ViewModel() {
    var uiState by mutableStateOf(RegistrationScreenUiState())
        private set

    var registerResult by mutableStateOf<Result<RegisterResponseDto>?>(null)
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

    fun changeRepeatedPassword(newText: String) {
        uiState = uiState.copy(
            repeatedPassword = newText
        )
    }

    fun register() {
        viewModelScope.launch {
            registerResult = try {
                Result.success(
                    registerUseCase(
                        RegisterRequestDto(
                            login = uiState.email,
                            password = uiState.password
                        )
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
        Log.d(
            "RegistratoinScreenViewModel", "result: ${registerResult.toString()}, " +
                    "request: ${RegisterRequestDto(login = uiState.email, password = uiState.password)}"
        )
    }
}