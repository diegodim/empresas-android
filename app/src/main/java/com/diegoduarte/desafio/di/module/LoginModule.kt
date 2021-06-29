package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.di.scope.LoginScope
import com.diegoduarte.desafio.login.LoginContract
import com.diegoduarte.desafio.login.LoginPresenter
import com.diegoduarte.desafio.login.view.LoginActivity
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class LoginModule {


    @LoginScope
    @Provides
    fun provideView(activity: LoginActivity) = activity as LoginContract.View

    @LoginScope
    @Provides
    fun providePresenter(repository: Repository,
                         view: LoginContract.View,
                         schedulerProvider: SchedulerProvider)
    = LoginPresenter(repository, view, schedulerProvider) as LoginContract.Presenter
}