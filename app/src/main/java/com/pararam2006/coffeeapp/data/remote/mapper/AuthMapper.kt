package com.pararam2006.coffeeapp.data.remote.mapper

import com.pararam2006.coffeeapp.data.remote.dto.LoginResponseNetworkDto
import com.pararam2006.coffeeapp.data.remote.dto.RegisterResponseNetworkDto
import com.pararam2006.coffeeapp.domain.dto.LoginResponseDto
import com.pararam2006.coffeeapp.domain.dto.RegisterResponseDto

fun LoginResponseNetworkDto.toDomain(): LoginResponseDto {
    return LoginResponseDto(
        token = token ?: throw IllegalStateException("Token is required"),
        tokenLifeTime = tokenLifeTime ?: 0
    )
}

fun RegisterResponseNetworkDto.toDomain(): RegisterResponseDto {
    return RegisterResponseDto(
        token = token ?: throw IllegalStateException("Token is required"),
        tokenLifeTime = tokenLifeTime ?: 0
    )
}