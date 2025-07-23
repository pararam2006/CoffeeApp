package com.pararam2006.coffeeapp.domain.usecase

import com.pararam2006.coffeeapp.data.remote.repository.LocationsRepository

class GetLocationsUseCase(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(token: String) = repository.getLocations(token)
}