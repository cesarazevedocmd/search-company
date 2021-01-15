package com.cesar.ioasysempresasandroid.login

import com.cesar.ioasysempresasandroid.general.IoasysError
import com.cesar.ioasysempresasandroid.api.IoasysAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel : LoginContract.Model {

    override fun login(request: LoginRequest, callback: LoginContract.Model.Callback) {
        val service = IoasysAPI.create(LoginService::class.java)
        service.login(request).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                when (response.isSuccessful) {
                    true -> {
                        val headers = response.headers()
                        callback.success(headers.get("uid"), headers.get("client"), headers.get("access-token"))
                    }
                    false -> callback.error(
                        IoasysError(response.code(), title = "Login error", response.message())
                    )
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                callback.error(
                    IoasysError(
                        title = "Login error",
                        message = t.message ?: "without message"
                    )
                )
            }
        })
    }
}