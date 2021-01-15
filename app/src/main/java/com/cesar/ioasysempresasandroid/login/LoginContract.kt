package com.cesar.ioasysempresasandroid.login

import com.cesar.ioasysempresasandroid.general.BaseContract

interface LoginContract {

    interface Model {

        fun login(request: LoginRequest, callback: Callback)

        interface Callback : BaseContract.Modal.Callback {
            fun success(uid: String?, client: String?, accessToken: String?)
        }
    }

    interface Presenter {
        fun login(email: String, password: String)
    }

    interface View : BaseContract.View<Presenter> {
        fun loginValid()
        fun showErrorEmailIsEmpty()
        fun showErrorPasswordIsEmpty()
        fun disableButton()
        fun enableButton()
        fun showLoading()
        fun hideLoading()
    }
}