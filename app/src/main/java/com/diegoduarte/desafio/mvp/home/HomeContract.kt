package com.diegoduarte.desafio.mvp.home

import com.diegoduarte.desafio.data.model.Enterprise

interface HomeContract {

    interface View{
        fun showEnterprises(enterprises: List<Enterprise>)
        fun showEmptySearch()
        fun showHomeLayout()
        fun returnToLogin()
        fun onItemClick(enterprise: Enterprise)
    }

    interface Presenter{
        fun searchByName(name: String)
        fun onCreate()
    }
}