package com.pararam2006.coffeeapp.domain.dto

data class LoginResponseDto(
    val token: String,
    val tokenLifeTime: Int
)