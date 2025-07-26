package com.pararam2006.coffeeapp.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import com.pararam2006.coffeeapp.domain.dto.MarkerDto
import com.pararam2006.coffeeapp.domain.usecase.GetLocationsUseCase
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapScreenViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _markers = MutableStateFlow<List<MarkerDto>>(emptyList())
    val markers: StateFlow<List<MarkerDto>> = _markers.asStateFlow()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val locations = getLocationsUseCase(token = tokenRepository.getTokenFromPrefs() ?: "")
            val newMarkers = locations.map { location ->
                MarkerDto(
                    point = Point(location.point.latitude, location.point.longitude),
                    name = location.name,
                    id = location.id // Передаем id
                )
            }
            _markers.value = newMarkers
        }
    }
} 