package com.pararam2006.coffeeapp.ui.map

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import com.pararam2006.coffeeapp.domain.dto.MarkerDto
import com.pararam2006.coffeeapp.domain.usecase.GetLocationsUseCase
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch

class MapScreenViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    var markers = mutableStateListOf<MarkerDto>()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val token = tokenRepository.getTokenFromPrefs() ?: ""
            val locations = getLocationsUseCase(token = token)
            val newMarkers = locations.map { location ->
                MarkerDto(
                    point = Point(location.point.latitude, location.point.longitude),
                    name = location.name,
                    id = location.id
                )
            }
            markers.run {
                clear()
                addAll(newMarkers)
            }
        }
    }
} 