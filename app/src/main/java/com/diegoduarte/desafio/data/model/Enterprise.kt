package com.diegoduarte.desafio.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Enterprise(

    val id: Int = 0,
    val enterprise_name: String = "",
    val photo: String = "",
    val description: String = "",
    val city: String = "",
    val country: String = "",
    val enterprise_type: EnterpriseType = EnterpriseType(0, "")

): Parcelable