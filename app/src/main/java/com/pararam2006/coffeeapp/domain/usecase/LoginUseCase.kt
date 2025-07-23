package com.pararam2006.coffeeapp.domain.usecase

import com.pararam2006.coffeeapp.data.remote.repository.AuthRepository
import com.pararam2006.coffeeapp.domain.dto.LoginRequestDto

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: LoginRequestDto) = repository.login(request)
}