package com.cesar.ioasysempresasandroid.login

import com.cesar.ioasysempresasandroid.app.IoasysApp
import com.cesar.ioasysempresasandroid.general.IoasysError

class LoginPresenter(
    private val view: LoginContract.View,
    private val model: LoginContract.Model
) : LoginContract.Presenter {

    override fun login(email: String, password: String) {

        if (invalidForm(email, password)) {
            notifyInvalidForm(email, password)
        } else {
            view.disableButton()
            view.showLoading()
            val login = LoginRequest(email, password)
            model.login(login, object : LoginContract.Model.Callback {
                override fun success(uid: String?, client: String?, accessToken: String?) {
                    IoasysApp.uid = uid
                    IoasysApp.client = client
                    IoasysApp.accessToken = accessToken

                    view.loginValid()
                    view.enableButton()
                    view.hideLoading()
                }

                override fun error(error: IoasysError) {
                    view.error(error)
                    view.enableButton()
                    view.hideLoading()
                }
            })
        }
    }

    private fun notifyInvalidForm(email: String, password: String) {
        if (email.isEmpty()) view.showErrorEmailIsEmpty()
        if (password.isEmpty()) view.showErrorPasswordIsEmpty()
    }

    private fun invalidForm(email: String, password: String) = email.isEmpty() || password.isEmpty()


}