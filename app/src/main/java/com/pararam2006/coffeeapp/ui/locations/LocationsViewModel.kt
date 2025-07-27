package com.pararam2006.coffeeapp.ui.locations

import android.util.Log
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
        private set
    var token = ""
        private set

    init {
        loadToken()
    }

    fun loadToken() {
        token = tokenRepository.getTokenFromPrefs() ?: ""
    }

    fun getLocations() {
        viewModelScope.launch {
            loadToken()
            val networkList = getLocationsUseCase(token)
            Log.d("LocationsAPI", networkList.toString())
            locationsList.run {
                clear()
                addAll(getLocationsUseCase(token))
            }
        }
    }
}