package com.diegoduarte.desafio.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token(
    @SerializedName("access_token")
    var accessToken: String? = "",
    var uid: String? = "",
    var client: String ?= ""

): Parcelable
