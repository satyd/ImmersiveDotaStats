package com.levp.immersivedotastats.domain.network

import com.google.gson.GsonBuilder
import com.levp.immersivedotastats.domain.network.interfaces.HeroApi
import com.levp.immersivedotastats.domain.network.interfaces.PlayerApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val playerApi: PlayerApi by lazy {
        Retrofit.Builder()
            .baseUrl(Path.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(PlayerApi::class.java)
    }

    val heroApi: HeroApi by lazy {
        Retrofit.Builder()
            .baseUrl(Path.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(HeroApi::class.java)
    }
}