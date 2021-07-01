package com.diegoduarte.desafio.mvp.login

import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.utils.Errors

interface LoginContract {
    interface View{
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showError(error: Errors)
        fun attemptLogin(token: Token)
    }

    interface Presenter{
        fun login(email: String, password: String)
    }
}