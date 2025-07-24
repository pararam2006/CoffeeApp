package com.pararam2006.coffeeapp.di

import com.pararam2006.coffeeapp.ui.auth.LoginScreenViewModel
import com.pararam2006.coffeeapp.ui.locations.LocationsViewModel
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::RegistrationScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::LocationsViewModel)
}