package com.cesar.ioasysempresasandroid.app

import com.cesar.ioasysempresasandroid.home.SearchCompanyContract
import com.cesar.ioasysempresasandroid.home.SearchCompanyModel
import com.cesar.ioasysempresasandroid.home.SearchCompanyPresenter
import com.cesar.ioasysempresasandroid.login.LoginContract
import com.cesar.ioasysempresasandroid.login.LoginModel
import com.cesar.ioasysempresasandroid.login.LoginPresenter
import org.koin.dsl.module

val login = module {
    factory<LoginContract.Model> { LoginModel() }

    factory<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(view, get()) }
}

val search = module {
    factory<SearchCompanyContract.Model> { SearchCompanyModel() }

    factory<SearchCompanyContract.Presenter> { (view: SearchCompanyContract.View) ->
        SearchCompanyPresenter(view, get())
    }
}

var ioasysModules = listOf(login, search)