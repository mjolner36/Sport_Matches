package com.mjo.sportmatches

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit

class NetworkService {
    private lateinit var mRetrofit: Retrofit

    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://livescore6.p.rapidapi.com/matches/v2/list-by-date?Category=soccer&Date=20220516")
        .get()
        .addHeader("X-RapidAPI-Host", "livescore6.p.rapidapi.com")
        .addHeader("X-RapidAPI-Key", "fc42b8fc95msh5b845373548d27bp115a3cjsncf06d7e4c9d8")
        .build()

    val response = client.newCall(request).execute()


}