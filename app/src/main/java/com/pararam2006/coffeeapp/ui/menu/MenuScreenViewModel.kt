package com.pararam2006.coffeeapp.ui.menu

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto
import com.pararam2006.coffeeapp.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.launch

class MenuScreenViewModel(
    private val getMenuUseCase: GetMenuUseCase,
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    val TAG = "MenuScreenViewModel"
    var menu = mutableStateListOf<MenuItemDto>()
        private set
    var token = ""
        private set

    init {
        loadToken()
    }

    fun loadToken() {
        token = tokenRepository.getTokenFromPrefs() ?: ""
    }

    fun loadMenu(cafeId: Int) {
        viewModelScope.launch {
            val useCaseResult = getMenuUseCase(token, cafeId)
            Log.d(TAG, useCaseResult.toString())
            menu.run {
                clear()
                addAll(useCaseResult)
            }
        }
    }
}