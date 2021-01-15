package com.cesar.ioasysempresasandroid.home

import com.cesar.ioasysempresasandroid.general.IoasysError

class SearchCompanyPresenter(
    private val view: SearchCompanyContract.View,
    private val model: SearchCompanyContract.Model
) : SearchCompanyContract.Presenter,
    SearchCompanyContract.Model.Callback {

    override fun search(name: String?) {
        name?.let {
            view.showDialog()
            if (it.trim().isEmpty())
                model.listAll(this)
            else model.searchCompany(it.trim(), this)
        }
    }

    override fun success(companies: List<Company>) {
        view.hideDialog()
        view.show(companies)
    }

    override fun noItemsFound() {
        view.hideDialog()
        view.showMessageItemsNotFound()
        view.clearList()
    }

    override fun error(error: IoasysError) {
        view.hideDialog()
        view.error(error)
    }
}