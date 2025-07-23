package com.pararam2006.coffeeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseNetworkDto(
    val token: String?,
    val tokenLifeTime: Int?
)
