package com.cesar.ioasysempresasandroid.home

import com.cesar.ioasysempresasandroid.api.IoasysAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchCompanyService {
    @GET("${IoasysAPI.api_version}/enterprises")
    fun search(@Query("name") name: String): Call<CompanyResponse>

    @GET("${IoasysAPI.api_version}/enterprises")
    fun listAll(): Call<CompanyResponse>
}