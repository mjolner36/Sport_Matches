package com.mjo.sportmatches.data

import com.google.gson.annotations.SerializedName

data class T1Response(
    @SerializedName("Img")
    var image:String,
    @SerializedName("Nm")
    var Nm:String
)
