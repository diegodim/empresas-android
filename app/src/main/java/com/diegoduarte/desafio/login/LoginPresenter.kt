package com.diegoduarte.desafio.login

import android.util.Log
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.utils.Errors
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider


class LoginPresenter(
    val repository: Repository,
    val view: LoginContract.View,
    val schedulerProvider: SchedulerProvider
): BasePresenter(), LoginContract.Presenter {



    override fun login(email: String, password: String) {
        view.showLoadingDialog()
        val disposable = repository.login(email, password)
            .observeOn(schedulerProvider.ui())
            ?.subscribeOn(schedulerProvider.io())
            ?.subscribe(
                {

                    if (it.isSuccessful) {
                        val token = Token()
                        token.access_token = it.headers().get("access-token")
                        token.uid = it.headers().get("uid")
                        token.client = it.headers().get("client")
                        view.attemptLogin(token)

                        repository.getEnterprise(token, "Fluoretiq Limited")
                            .observeOn(schedulerProvider.ui())
                            ?.subscribeOn(schedulerProvider.io())
                            ?.subscribe()

                    }else{

                        view.showError(Errors.LOGIN_ERROR)
                    }
                },
                {
                    view.showError(Errors.INTERNET_ERROR)
                    view.hideLoadingDialog()
                },
                {
                    view.hideLoadingDialog()
                }
            )
        addDisposable(disposable!!)
    }

}