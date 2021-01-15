package com.cesar.ioasysempresasandroid.home

import com.cesar.ioasysempresasandroid.api.IoasysAPI
import com.cesar.ioasysempresasandroid.general.IoasysError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCompanyModel : SearchCompanyContract.Model {

    private val service = IoasysAPI.create(SearchCompanyService::class.java)

    override fun searchCompany(name: String, callback: SearchCompanyContract.Model.Callback) {
        service.search(name).enqueue(getBaseCallback(callback))
    }

    override fun listAll(callback: SearchCompanyContract.Model.Callback) {
        service.listAll().enqueue(getBaseCallback(callback))
    }

    private fun getBaseCallback(callback: SearchCompanyContract.Model.Callback) =
        object : Callback<CompanyResponse> {
            override fun onResponse(
                call: Call<CompanyResponse>,
                response: Response<CompanyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.enterprises.isEmpty())
                            callback.noItemsFound()
                        else
                            callback.success(it.enterprises)
                    }
                } else {
                    callback.error(getErrorByCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<CompanyResponse>, t: Throwable) {
                callback.error(
                    IoasysError(
                        title = "Search Company Error",
                        message = t.message ?: "without message"
                    )
                )
            }
        }

    private fun getErrorByCode(code: Int, defaultError: String): IoasysError {
        val title = "Error Search Items"
        val message = when (code) {
            401 -> "Sem autorização para executar a solicitação"
            else -> defaultError
        }
        return IoasysError(code, title, message)
    }
}