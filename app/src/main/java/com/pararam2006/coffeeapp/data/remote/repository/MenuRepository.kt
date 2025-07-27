package com.pararam2006.coffeeapp.data.remote.repository

import com.pararam2006.coffeeapp.data.remote.api.CoffeeApi
import com.pararam2006.coffeeapp.data.remote.mapper.toDomain
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto

class MenuRepository(
    private val api: CoffeeApi
) {
    suspend fun getMenu(token: String, locationId: Int): List<MenuItemDto> =
        api.getMenu("Bearer $token", locationId).map { it.toDomain() }
}
