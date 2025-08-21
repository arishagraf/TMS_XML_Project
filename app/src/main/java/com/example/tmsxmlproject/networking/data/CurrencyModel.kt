package com.example.tmsxmlproject.networking.data

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("Cur_ID")
    val curID: Int,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Cur_Abbreviation")
    val curAbbreviation: String,
    @SerializedName("Cur_Scale")
    val curScale: Int,
    @SerializedName("Cur_Name")
    val curName: String,
    @SerializedName("Cur_OfficialRate")
    val curOfficialRate: Double,
)