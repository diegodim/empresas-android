package com.diegoduarte.desafio.mvp.enterprise

import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Enterprise

class EnterprisePresenter(val view: EnterpriseContract.View, val enterprise: Enterprise)
    : BasePresenter(), EnterpriseContract.Presenter {

    override fun getEnterprise() {
        view.showEnterprise(enterprise)
    }
}