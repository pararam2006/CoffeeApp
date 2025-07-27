package com.pararam2006.coffeeapp.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class MenuItemDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Int,
    var count: Int,
)
