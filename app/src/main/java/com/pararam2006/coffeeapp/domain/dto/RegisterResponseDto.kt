package com.pararam2006.coffeeapp.domain.dto

data class RegisterResponseDto(
    val token: String,
    val tokenLifeTime: Int
)
