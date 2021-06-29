package com.diegoduarte.desafio.home

import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider

class HomePresenter(
    val repository: Repository,
    val view: HomeContract.View,
    val schedulerProvider: SchedulerProvider,
    val token: Token
) : HomeContract.Presenter, BasePresenter() {

}