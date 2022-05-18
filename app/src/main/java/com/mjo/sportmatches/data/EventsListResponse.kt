package com.mjo.sportmatches.data

import com.google.gson.annotations.SerializedName

data class EventsListResponse(
    @SerializedName("T1")
    var team1: T1Response,
    @SerializedName("T2")
    var team2: T2Response
)
