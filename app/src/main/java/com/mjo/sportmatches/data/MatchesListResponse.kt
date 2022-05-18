package com.mjo.sportmatches.data

import com.google.gson.annotations.SerializedName
import com.mjo.sportmatches.data.EventsListResponse

data class MatchesListResponse(

    @SerializedName("Snm")
    var nameOfTournament:String,

    @SerializedName("Events")
    var Events: List<EventsListResponse>
)
