package com.diegoduarte.desafio.base

import com.diegoduarte.desafio.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApp: DaggerApplication() {


    private lateinit var injector: AndroidInjector<out DaggerApplication>

    // Return the Dagger Injector for the APP
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return injector
    }

    // Create the APP an build the modules of APP
    override fun onCreate() {

        injector = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}