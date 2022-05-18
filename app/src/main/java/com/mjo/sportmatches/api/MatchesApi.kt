package com.mjo.sportmatches.api


import com.mjo.sportmatches.dataclasses.Matches
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.Headers

interface MatchesApi {

    @Headers(
            "X-RapidAPI-Host: livescore6.p.rapidapi.com",
            "X-RapidAPI-Key: e023fdbc0cmshf8a476c26e0d0d9p16453ejsne7633f6de9e1"
    )

    @GET("./matches/v2/list-by-date") //
    fun getMatchesList(@Query("Category") sport:String, @Query("Date") date:String): Call<Matches>
}

object RetrofitInstance {
    var api = Retrofit.Builder()
        .baseUrl("https://livescore6.p.rapidapi.com")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MatchesApi::class.java)
}
