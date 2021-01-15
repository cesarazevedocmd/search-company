package com.cesar.ioasysempresasandroid.login

import com.cesar.ioasysempresasandroid.api.IoasysAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("${IoasysAPI.api_version}/users/auth/sign_in")
    fun login(@Body request: LoginRequest): Call<Any>
}