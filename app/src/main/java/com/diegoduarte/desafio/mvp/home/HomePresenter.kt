package com.diegoduarte.desafio.mvp.home

import com.diegoduarte.desafio.base.BaseObserver
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprises
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.mvp.home.domain.GetEnterprises
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import retrofit2.Response


class HomePresenter(
    val repository: Repository,
    val view: HomeContract.View,
    val schedulerProvider: SchedulerProvider,
    val token: Token
) : HomeContract.Presenter, BasePresenter() {

    private var getEnterprises: GetEnterprises = GetEnterprises(repository,
        schedulerProvider.io(),
        schedulerProvider.ui())

    // Create the presenter
    override fun onCreate() {
        view.showHomeLayout()
    }

    // Search the enterprise on Api Repository
    override fun searchByName(name: String) {

        val disposable = getEnterprises.execute(EnterprisesObserver(),
            GetEnterprises.Params(token, name) )
        addDisposable(disposable)
    }

    inner class EnterprisesObserver: BaseObserver<Response<Enterprises>>() {
        override fun onNext(t: Response<Enterprises>) {
            if (t.isSuccessful) {
                // If return a successful login
                if (t.body()!!.enterprises.isNotEmpty()) {
                    // If has return enterprises
                    view.showEnterprises(t.body()!!.enterprises)
                } else {
                    // If has not return enterprises
                    view.showEmptySearch()
                }
            } else if (t.code() == 401) {
                // If return a invalid Auth
                view.returnToLogin()
            }
        }

        override fun onError(exception: Throwable) {
            // If has any error
            view.showEmptySearch()
        }


    }

}