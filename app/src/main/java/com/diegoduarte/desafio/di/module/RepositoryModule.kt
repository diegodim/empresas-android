package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.data.source.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository() = RemoteRepository() as Repository

}