package com.pararam2006.coffeeapp.data.remote.dto

data class LocationsNetworkDto(
    val id: Int?,
    val name: String?,
    val point: PointNetworkDto?
)

data class PointNetworkDto(
    val latitude: Int?,
    val longitude: Int?,
)