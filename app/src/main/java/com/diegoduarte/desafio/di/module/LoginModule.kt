package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.di.scope.LoginScope
import com.diegoduarte.desafio.mvp.login.LoginContract
import com.diegoduarte.desafio.mvp.login.LoginPresenter
import com.diegoduarte.desafio.mvp.login.view.LoginActivity
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    //Provide a View only on LoginScope
    @LoginScope
    @Provides
    fun provideView(activity: LoginActivity) = activity as LoginContract.View

    //Provide a Presenter just only on LoginScope
    @LoginScope
    @Provides
    fun providePresenter(repository: Repository,
                         view: LoginContract.View,
                         schedulerProvider: SchedulerProvider)
    = LoginPresenter(repository, view, schedulerProvider) as LoginContract.Presenter
}