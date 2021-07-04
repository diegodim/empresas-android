package com.diegoduarte.desafio.di.module

import android.os.Bundle
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.di.scope.HomeScope
import com.diegoduarte.desafio.mvp.home.HomeContract
import com.diegoduarte.desafio.mvp.home.HomePresenter
import com.diegoduarte.desafio.mvp.home.view.HomeFragment
import com.diegoduarte.desafio.mvp.home.view.HomeFragmentArgs
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    // Provide a Token from then intent just only on HomeScope
    @HomeScope
    @Provides
    fun provideToken(fragment: HomeFragment): Token {

        val args = HomeFragmentArgs.fromBundle(fragment.arguments as Bundle)
        return args.token
    }

    //Provide a View just only on HomeScope
    @HomeScope
    @Provides
    fun provideView(fragment: HomeFragment) = fragment as HomeContract.View

    //Provide a Presenter just only on HomeScope
    @HomeScope
    @Provides
    fun providePresenter(repository: Repository,
                         view: HomeContract.View,
                         schedulerProvider: SchedulerProvider,
                         token: Token)
            = HomePresenter(repository, view, schedulerProvider, token) as HomeContract.Presenter
}