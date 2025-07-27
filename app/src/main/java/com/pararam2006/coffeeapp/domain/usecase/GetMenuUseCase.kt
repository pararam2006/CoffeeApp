package com.pararam2006.coffeeapp.domain.usecase

import com.pararam2006.coffeeapp.data.remote.repository.MenuRepository
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto

class GetMenuUseCase(
    private val repository: MenuRepository
) {
    suspend operator fun invoke(token: String, locationId: Int): List<MenuItemDto> =
        repository.getMenu(token, locationId)
}