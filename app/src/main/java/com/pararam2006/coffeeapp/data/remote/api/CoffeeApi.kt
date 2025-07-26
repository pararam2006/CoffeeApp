package com.pararam2006.coffeeapp.data.remote.api

import com.pararam2006.coffeeapp.data.remote.dto.LocationsNetworkDto
import com.pararam2006.coffeeapp.data.remote.dto.LoginResponseNetworkDto
import com.pararam2006.coffeeapp.data.remote.dto.MenuItemNetworkDto
import com.pararam2006.coffeeapp.data.remote.dto.RegisterResponseNetworkDto
import com.pararam2006.coffeeapp.domain.dto.LoginRequestDto
import com.pararam2006.coffeeapp.domain.dto.RegisterRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CoffeeApi {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseNetworkDto

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseNetworkDto

    @GET("/locations")
    suspend fun getLocations(
        @Header("Authorization") token: String
    ): List<LocationsNetworkDto>

    @GET("/location/{locationId}/menu")
    suspend fun getMenu(
        @Header("Authorization") token: String,
        @Path("locationId") locationId: Int,
    ): List<MenuItemNetworkDto>
}
