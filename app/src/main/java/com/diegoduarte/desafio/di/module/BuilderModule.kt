package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.di.scope.EnterpriseScope
import com.diegoduarte.desafio.di.scope.HomeScope
import com.diegoduarte.desafio.di.scope.LoginScope
import com.diegoduarte.desafio.mvp.enterprise.view.EnterpriseActivity
import com.diegoduarte.desafio.mvp.home.view.HomeFragment
import com.diegoduarte.desafio.mvp.login.view.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    // Build the modules for LoginFragment
    @LoginScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindMoviesFragment(): LoginFragment

    // Build the modules for HomeFragment
    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindMovieFragment(): HomeFragment

    // Build the modules for EnterpriseFragment
    @EnterpriseScope
    @ContributesAndroidInjector(modules = [EnterpriseModule::class])
    abstract fun bindEnterpriseActivity(): EnterpriseActivity

}