package com.pararam2006.coffeeapp.di

import com.pararam2006.coffeeapp.data.remote.repository.AuthRepository
import com.pararam2006.coffeeapp.data.remote.repository.LocationsRepository
import com.pararam2006.coffeeapp.data.remote.repository.TokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { LocationsRepository(get()) }
    single { TokenRepository(get()) }
}