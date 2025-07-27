package com.pararam2006.coffeeapp.domain.dto

import com.yandex.mapkit.geometry.Point

data class MarkerDto(
    val point: Point,
    val name: String,
    val id: Int
)
