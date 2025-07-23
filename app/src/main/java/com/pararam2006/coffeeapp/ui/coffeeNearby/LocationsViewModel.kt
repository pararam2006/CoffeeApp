package com.pararam2006.coffeeapp.ui.coffeeNearby

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import com.pararam2006.coffeeapp.domain.dto.LocationsDto
import com.pararam2006.coffeeapp.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    var locationsList = mutableStateListOf<LocationsDto>()
    var token = ""
        private set

    fun getLocations() {
        viewModelScope.launch {
            locationsList.run {
                clear()
                addAll(listOf(getLocationsUseCase(token)))
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            tokenRepository.saveTokenToPrefs(token)
        }
    }

    fun loadToken() {
        viewModelScope.launch {
            token = tokenRepository.getTokenFromPrefs() ?: ""
        }
    }
}