package com.diegoduarte.desafio.home

import androidx.appcompat.widget.SearchView
import com.diegoduarte.desafio.data.model.Enterprise
import io.reactivex.rxjava3.disposables.Disposable

interface HomeContract {

    interface View{
        fun showEnterprises(enterprises: List<Enterprise>)
        fun showEmptySearch()
        fun showHomeLayout()
        fun returnToLogin()
    }

    interface Presenter{
        fun searchByName(name: String)
        fun onCreate()
    }
}