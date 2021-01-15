package com.cesar.ioasysempresasandroid.general

interface BaseContract {

    interface Modal {
        interface Callback {
            fun error(error: IoasysError)
        }
    }

    interface View<T> {
        val presenter: T
        fun error(error: IoasysError)
    }
}