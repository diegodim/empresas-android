package com.diegoduarte.desafio.data.model

data class Enterprise(

    val id: Int,
    val enterprise_name: String,
    val photo: String,
    val description: String,
    val city: String,
    val country: String,
    val enterprise_type: EnterpriseType

)