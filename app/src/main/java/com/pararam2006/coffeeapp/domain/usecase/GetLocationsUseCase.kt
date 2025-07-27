package com.pararam2006.coffeeapp.domain.usecase

import com.pararam2006.coffeeapp.data.remote.repository.LocationsRepository
import com.pararam2006.coffeeapp.domain.dto.LocationsDto

class GetLocationsUseCase(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(token: String): List<LocationsDto> = repository.getLocations(token)
}