package com.pararam2006.coffeeapp.di

import com.pararam2006.coffeeapp.ui.auth.LoginScreenViewModel
import com.pararam2006.coffeeapp.ui.locations.LocationsViewModel
import com.pararam2006.coffeeapp.ui.menu.MenuScreenViewModel
import com.pararam2006.coffeeapp.ui.registration.RegistrationScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.pararam2006.coffeeapp.ui.map.MapScreenViewModel
import com.pararam2006.coffeeapp.ui.order.OrderScreenViewModel

val viewModelModule = module {
    viewModelOf(::RegistrationScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::LocationsViewModel)
    viewModelOf(::MenuScreenViewModel)
    viewModelOf(::MapScreenViewModel)
    viewModelOf(::OrderScreenViewModel)
}