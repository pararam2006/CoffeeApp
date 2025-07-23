package com.pararam2006.coffeeapp.di

import com.pararam2006.coffeeapp.domain.usecase.GetLocationsUseCase
import com.pararam2006.coffeeapp.domain.usecase.LoginUseCase
import com.pararam2006.coffeeapp.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { RegisterUseCase(get()) }
    single { GetLocationsUseCase(get()) }
}