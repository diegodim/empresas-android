package com.diegoduarte.desafio.mvp.login

import com.diegoduarte.desafio.base.BaseObserver
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.mvp.login.domain.Authenticate
import com.diegoduarte.desafio.utils.Errors
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import retrofit2.Response


class LoginPresenter(
    val repository: Repository,
    val view: LoginContract.View,
    val schedulerProvider: SchedulerProvider
): BasePresenter(), LoginContract.Presenter {

    private val authenticate: Authenticate = Authenticate(repository,
        schedulerProvider.io(),
        schedulerProvider.ui())


    override fun login(email: String, password: String) {
        view.showLoadingDialog()

        val disposable = authenticate.execute(LoginObserver(),
            Authenticate.Params(email, password))

        addDisposable(disposable)
    }

    inner class LoginObserver: BaseObserver<Response<LoginResponse>>() {
        override fun onNext(t: Response<LoginResponse>) {
            if (t.isSuccessful && t.body()!!.success) {
                // If the login Auth successful
                val token = Token()
                token.access_token = t.headers().get("access-token")
                token.uid = t.headers().get("uid")
                token.client = t.headers().get("client")
                view.attemptLogin(token)

            }else{
                // If the login Auth error
                view.showError(Errors.LOGIN_ERROR)
                view.hideLoadingDialog()
            }
        }

        override fun onError(exception: Throwable) {
            // If has any error
            view.showError(Errors.INTERNET_ERROR)
            view.hideLoadingDialog()
        }

    }


}