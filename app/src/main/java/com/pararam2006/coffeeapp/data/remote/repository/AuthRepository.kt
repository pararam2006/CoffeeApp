package com.pararam2006.coffeeapp.data.remote.repository

import com.pararam2006.coffeeapp.data.remote.api.CoffeeApi
import com.pararam2006.coffeeapp.data.remote.mapper.toDomain
import com.pararam2006.coffeeapp.domain.dto.LoginRequestDto
import com.pararam2006.coffeeapp.domain.dto.LoginResponseDto
import com.pararam2006.coffeeapp.domain.dto.RegisterRequestDto
import com.pararam2006.coffeeapp.domain.dto.RegisterResponseDto

class AuthRepository(
    private val api: CoffeeApi
) {
    suspend fun login(request: LoginRequestDto): LoginResponseDto =
        api.login(request).toDomain()

    suspend fun register(requestDto: RegisterRequestDto): RegisterResponseDto =
        api.register(requestDto).toDomain()
}