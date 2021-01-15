package com.cesar.ioasysempresasandroid.api

import com.cesar.ioasysempresasandroid.app.IoasysApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IoasysAPI {

    companion object {

        private var instance: Retrofit? = null
        private const val url = "https://empresas.ioasys.com.br/"
        const val api_version = "api/v1"

        private fun getInstance(): Retrofit {
            return instance ?: synchronized(this) {
                instance = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()
                instance!!
            }
        }

        fun <T> create(serviceClass: Class<T>): T {
            return getInstance().create(serviceClass)
        }

        private fun getHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newBuilder = chain.request().newBuilder().apply {
                        header("Content-Type", "application/json")
                        IoasysApp.uid?.let { header("uid", it) }
                        IoasysApp.client?.let { header("client", it) }
                        IoasysApp.accessToken?.let { header("access-token", it) }
                    }.build()
                    chain.proceed(newBuilder)
                }.build()
        }
    }
}