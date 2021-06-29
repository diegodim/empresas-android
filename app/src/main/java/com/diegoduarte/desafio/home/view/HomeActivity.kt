package com.diegoduarte.desafio.home.view

import android.os.Bundle
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.home.HomeContract
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    companion object {
        const val INTENT_EXTRA_TOKEN= "token"
    }


    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContent(): Int = R.layout.activity_home

    override fun getPresenter(): BasePresenter = presenter as BasePresenter
}