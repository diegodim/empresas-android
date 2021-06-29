package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.utils.schedulers.AppSchedulerProvider
import com.diegoduarte.desafio.utils.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Singleton
    @Provides
    fun provideSchedulers() = AppSchedulerProvider() as SchedulerProvider
}