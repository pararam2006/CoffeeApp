package com.pararam2006.coffeeapp.domain.dto

data class LocationsDto(
    val id: Int,
    val name: String,
    val point: PointDto
)

data class PointDto(
    val latitude: Int,
    val longitude: Int,
)