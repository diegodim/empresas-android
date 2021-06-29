package com.diegoduarte.desafio.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token(
    var access_token: String? = "",
    var uid: String? = "",
    var client: String ?= ""

): Parcelable
