package com.sumuzu.anggotaapp.config

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {

    fun getRetrofit() : Retrofit{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl("http://192.168.100.35/kotlinudacoding/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)

}