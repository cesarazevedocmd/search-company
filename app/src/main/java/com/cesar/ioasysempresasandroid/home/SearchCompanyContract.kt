package com.cesar.ioasysempresasandroid.home

import com.cesar.ioasysempresasandroid.general.BaseContract

interface SearchCompanyContract {

    interface Model {
        fun searchCompany(name: String, callback: Callback)
        fun listAll(callback: Callback)

        interface Callback : BaseContract.Modal.Callback {
            fun success(companies: List<Company>)
            fun noItemsFound()
        }
    }

    interface Presenter {
        fun search(name: String?)
    }

    interface View : BaseContract.View<Presenter> {
        fun showMessageItemsNotFound()
        fun showDialog()
        fun hideDialog()
        fun show(companies: List<Company>)
        fun clearList()
    }

}