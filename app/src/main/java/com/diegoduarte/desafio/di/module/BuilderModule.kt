package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.di.scope.HomeScope
import com.diegoduarte.desafio.di.scope.LoginScope
import com.diegoduarte.desafio.home.view.HomeActivity
import com.diegoduarte.desafio.login.view.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @LoginScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindMoviesActivity(): LoginActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindMovieActivity(): HomeActivity

}