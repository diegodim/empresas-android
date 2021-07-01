package com.diegoduarte.desafio.di.module

import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.di.scope.EnterpriseScope
import com.diegoduarte.desafio.mvp.enterprise.view.EnterpriseActivity
import com.diegoduarte.desafio.mvp.enterprise.EnterpriseContract
import com.diegoduarte.desafio.mvp.enterprise.EnterprisePresenter
import dagger.Module
import dagger.Provides

@Module
class EnterpriseModule {
    // Provide a Enterprise from then intent just only on EnterpriseScope
    @EnterpriseScope
    @Provides
    fun provideEnterprise(activity: EnterpriseActivity): Enterprise {
        if (!activity.intent.hasExtra(EnterpriseActivity.INTENT_EXTRA_ENTERPRISE)) {
            throw IllegalArgumentException("Activity is missing extra enterprise parameter")
        }
        return activity.intent.extras?.getParcelable(EnterpriseActivity.INTENT_EXTRA_ENTERPRISE)!!
    }
    //Provide a View just only on EnterpriseScope
    @EnterpriseScope
    @Provides
    fun provideView(activity: EnterpriseActivity) = activity as EnterpriseContract.View

    //Provide a Presenter just only on EnterpriseScope
    @EnterpriseScope
    @Provides
    fun providePresenter(view: EnterpriseContract.View, enterprise: Enterprise)
        = EnterprisePresenter(view, enterprise) as EnterpriseContract.Presenter
}