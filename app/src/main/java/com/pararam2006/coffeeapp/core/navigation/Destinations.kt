package com.pararam2006.coffeeapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Register

@Serializable
object Auth

@Serializable
data class Menu(val id: Int)

@Serializable
object Locations

@Serializable
object CoffeeMap

@Serializable
data class Order(val menuItemsJson: String)
