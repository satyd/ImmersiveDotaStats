package com.levp.immersivedotastats.data.repository

import com.google.gson.GsonBuilder
import com.levp.immersivedotastats.data.remote.interfaces.HeroApi
import com.levp.immersivedotastats.data.remote.interfaces.PlayerApi
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