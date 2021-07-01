package com.diegoduarte.desafio.di.component

import android.app.Application
import com.diegoduarte.desafio.base.BaseApp
import com.diegoduarte.desafio.di.module.AppModule
import com.diegoduarte.desafio.di.module.BuilderModule
import com.diegoduarte.desafio.di.module.RepositoryModule
import com.diegoduarte.desafio.di.module.SchedulerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    BuilderModule::class, RepositoryModule::class, SchedulerModule::class])
interface AppComponent: AndroidInjector<BaseApp> {

    override fun inject(app: BaseApp)
    // Build the components o DI
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}