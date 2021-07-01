package com.diegoduarte.desafio.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnterpriseType(
    val id: Int,
    val enterprise_type_name: String
): Parcelable
