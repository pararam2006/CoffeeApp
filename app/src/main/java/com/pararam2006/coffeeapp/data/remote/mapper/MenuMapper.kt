package com.pararam2006.coffeeapp.data.remote.mapper

import com.pararam2006.coffeeapp.data.remote.dto.MenuItemNetworkDto
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto

fun MenuItemNetworkDto.toDomain(): MenuItemDto {
    return MenuItemDto(
        id = id ?: 0,
        name = name ?: "Unknown name",
        imageUrl = imageUrl ?: "",
        price = price ?: 0,
        count = 0
    )
}