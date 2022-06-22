package com.usaha.umkm.utility

import com.google.gson.GsonBuilder
import com.usaha.umkm.data.model.UMKMRoute
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkConfig {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getInterceptor() : OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return  okHttpClient
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://lbs.sunistheworld.com/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getService() = getRetrofit().create(UMKMRoute::class.java)
}