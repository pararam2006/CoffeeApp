package com.pararam2006.coffeeapp.data.remote.repository

import com.pararam2006.coffeeapp.data.remote.api.CoffeeApi
import com.pararam2006.coffeeapp.data.remote.mapper.toDomain
import com.pararam2006.coffeeapp.domain.dto.LocationsDto

class LocationsRepository(
    private val api: CoffeeApi
) {
    suspend fun getLocations(token: String): LocationsDto = api.getLocations(token).toDomain()
}