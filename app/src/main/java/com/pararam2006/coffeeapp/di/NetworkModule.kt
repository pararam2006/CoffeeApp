package com.pararam2006.coffeeapp.di

import com.pararam2006.coffeeapp.data.remote.api.CoffeeApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://212.41.30.90:35005")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(CoffeeApi::class.java) }
    single { OkHttpClient() }
}