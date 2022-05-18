package com.mjo.sportmatches.api

import com.mjo.sportmatches.data.StageListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.Headers

interface MatchesApi {

    @Headers(
            "X-RapidAPI-Host: livescore6.p.rapidapi.com",
            "X-RapidAPI-Key: fc42b8fc95msh5b845373548d27bp115a3cjsncf06d7e4c9d8"
    )

    @GET("./matches/v2/list-by-date")
    fun getMatchesList(@Query("Category") sport:String, @Query("Date") date:String): Single<StageListResponse>
}

object RetrofitInstance {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://livescore6.p.rapidapi.com/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StageListResponse::class.java)
}
