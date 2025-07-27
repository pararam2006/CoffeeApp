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
            Log.d("MenuScreenViewModel", useCaseResult.toString())
            menu.run {
                clear()
                addAll(useCaseResult)
            }
        }
    }

    fun incrementMenuItemCount(id: Int) {
        val index = menu.indexOfFirst { it.id == id }
        if (index != -1) {
            val currentItem = menu[index]
            if (currentItem.count < 99) {
                menu[index] = currentItem.copy(count = currentItem.count + 1)
            }
        }
    }

    fun decrementMenuItemCount(id: Int) {
        val index = menu.indexOfFirst { it.id == id }
        if (index != -1) {
            val currentItem = menu[index]
            if (currentItem.count > 0) {
                menu[index] = currentItem.copy(count = currentItem.count - 1)
            }
        }
    }
}