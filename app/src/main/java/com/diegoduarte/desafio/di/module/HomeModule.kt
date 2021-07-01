package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.di.scope.HomeScope
import com.diegoduarte.desafio.home.HomeContract
import com.diegoduarte.desafio.home.HomePresenter
import com.diegoduarte.desafio.home.view.HomeActivity
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    // Provide a Token from then intent just only on HomeScope
    @HomeScope
    @Provides
    fun provideToken(activity: HomeActivity): Token {
        if (!activity.intent.hasExtra(HomeActivity.INTENT_EXTRA_TOKEN)) {
            throw IllegalArgumentException("Activity is missing extra token parameter")
        }
        return activity.intent.extras?.getParcelable(HomeActivity.INTENT_EXTRA_TOKEN)!!
    }

    //Provide a View from then intent just only on HomeScope
    @HomeScope
    @Provides
    fun provideView(activity: HomeActivity) = activity as HomeContract.View

    //Provide a Presenter from then intent just only on HomeScope
    @HomeScope
    @Provides
    fun providePresenter(repository: Repository,
                         view: HomeContract.View,
                         schedulerProvider: SchedulerProvider,
                         token: Token)
            = HomePresenter(repository, view, schedulerProvider, token) as HomeContract.Presenter
}