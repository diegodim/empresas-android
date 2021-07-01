package com.diegoduarte.desafio.mvp.enterprise

import com.diegoduarte.desafio.data.model.Enterprise

interface EnterpriseContract {

    interface View{
        fun showEnterprise(enterprise: Enterprise)

    }

    interface Presenter{
        fun getEnterprise()
    }
}