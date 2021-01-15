package com.cesar.ioasysempresasandroid.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cesar.ioasysempresasandroid.R
import com.cesar.ioasysempresasandroid.general.IoasysError
import com.cesar.ioasysempresasandroid.util.goTo
import com.cesar.ioasysempresasandroid.util.hide
import com.cesar.ioasysempresasandroid.home.HomeActivity
import com.cesar.ioasysempresasandroid.util.show
import kotlinx.android.synthetic.main.login_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }

    override fun loginValid() {
        goTo(HomeActivity::class.java)
    }

    override fun error(error: IoasysError) {
        login_activity_edt_email.error = ""
        login_activity_edt_password.error = getString(R.string.invalid_credentials)
    }

    override fun showErrorEmailIsEmpty() {
        login_activity_edt_email.error = getString(R.string.email_can_not_be_empty)
    }

    override fun showErrorPasswordIsEmpty() {
        login_activity_edt_password.error = getString(R.string.password_can_not_be_empty)
    }

    override fun disableButton() {
        login_activity_btn_enter.isEnabled = false
    }

    override fun enableButton() {
        login_activity_btn_enter.isEnabled = true
    }

    override fun showLoading() {
        login_activity_pgb_loading.show()
    }

    override fun hideLoading() {
        login_activity_pgb_loading.hide()
    }

    fun login(view: View) {
        clearErrorFields()
        presenter.login(
            login_activity_edt_email.editText!!.text.toString(),
            login_activity_edt_password.editText!!.text.toString()
        )
    }

    private fun clearErrorFields() {
        login_activity_edt_email.error = null
        login_activity_edt_password.error = null
    }
}