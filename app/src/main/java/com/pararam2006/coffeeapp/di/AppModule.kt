package com.pararam2006.coffeeapp.di

import org.koin.dsl.module

val appModule = module {
    includes(
        viewModelModule,
        networkModule,
        useCaseModule,
        repositoryModule,
    )
}