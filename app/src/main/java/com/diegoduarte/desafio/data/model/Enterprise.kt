package com.diegoduarte.desafio.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Enterprise(

    val id: Int = 0,
    @SerializedName("enterprise_name")
    val enterpriseName: String = "",
    val photo: String = "",
    val description: String = "",
    val city: String = "",
    val country: String = "",
    @SerializedName("enterprise_type")
    val enterpriseType: EnterpriseType = EnterpriseType(0, "")

): Parcelable