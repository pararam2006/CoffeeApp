package com.pararam2006.coffeeapp.ui.order

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto
import kotlin.collections.indexOfFirst

class OrderScreenViewModel(
) : ViewModel() {

    val orderItems = mutableStateListOf<MenuItemDto>()

    fun loadOrderItems(items: List<MenuItemDto>) {
        orderItems.run {
            clear()
            addAll(items)
        }
    }

    fun incrementOrderItemCount(id: Int) {
        val index = orderItems.indexOfFirst { it.id == id }
        if (index != -1) {
            val currentItem = orderItems[index]
            if (currentItem.count < 99) {
                orderItems[index] = currentItem.copy(count = currentItem.count + 1)
            }
        }
    }

    fun decrementOrderItemCount(id: Int) {
        val index = orderItems.indexOfFirst { it.id == id }
        if (index != -1) {
            val currentItem = orderItems[index]
            if (currentItem.count > 0) {
                orderItems[index] = currentItem.copy(count = currentItem.count - 1)
            }
        }
    }
}