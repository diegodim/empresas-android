package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.di.scope.EnterpriseScope
import com.diegoduarte.desafio.di.scope.HomeScope
import com.diegoduarte.desafio.di.scope.LoginScope
import com.diegoduarte.desafio.mvp.enterprise.view.EnterpriseActivity
import com.diegoduarte.desafio.mvp.home.view.HomeActivity
import com.diegoduarte.desafio.mvp.login.view.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    // Build the modules for LoginActivity
    @LoginScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindMoviesActivity(): LoginActivity

    // Build the modules for HomeActivity
    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindMovieActivity(): HomeActivity

    // Build the modules for EnterpriseActivity
    @EnterpriseScope
    @ContributesAndroidInjector(modules = [EnterpriseModule::class])
    abstract fun bindEnterpriseActivity(): EnterpriseActivity

}