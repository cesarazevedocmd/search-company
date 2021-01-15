package com.cesar.ioasysempresasandroid.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val id: Int,
    @SerializedName("enterprise_name") val enterpriseName: String,
    val photo: String,
    val description: String,
    val city: String,
    val country: String,
    val value: Int,
    @SerializedName("share_price") val sharePrice: Double,
    @SerializedName("enterprise_type") val enterpriseType: CompanyType
) : Parcelable {
    companion object {
        const val flag = "FL_COMPANY"
    }
}

@Parcelize
data class CompanyType(
    val id: Int,
    @SerializedName("enterprise_type_name") val name: String
) : Parcelable