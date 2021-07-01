package com.diegoduarte.desafio.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    // Create the injection of App context
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application
}