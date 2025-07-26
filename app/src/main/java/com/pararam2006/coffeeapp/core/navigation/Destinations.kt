package com.pararam2006.coffeeapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Register

@Serializable
object Auth

@Serializable
object Locations

@Serializable
object CoffeeMap

@Serializable
data class Menu(val id: Int)

@Serializable
object Order
