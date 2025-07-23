package com.pararam2006.coffeeapp.data.remote.mapper

import com.pararam2006.coffeeapp.data.remote.dto.LocationsNetworkDto
import com.pararam2006.coffeeapp.data.remote.dto.PointNetworkDto
import com.pararam2006.coffeeapp.domain.dto.LocationsDto
import com.pararam2006.coffeeapp.domain.dto.PointDto

fun LocationsNetworkDto.toDomain(): LocationsDto {
    return LocationsDto(
        id = id ?: 0,
        name = name ?: "Unknown name",
        point = point?.toDomain() ?: PointDto(latitude = 0, longitude = 0)
    )
}

fun PointNetworkDto.toDomain(): PointDto {
    return PointDto(
        latitude = latitude ?: 0,
        longitude = longitude ?: 0,
    )
}