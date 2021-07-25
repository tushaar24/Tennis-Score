package com.example.tennisscores.data.retrofit

import com.example.tennisscores.data.entities.AtpRanking
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RemoteServicesApi {
    @GET("/rankings/ATP")
    @Headers("x-rapidapi-key: 324632024fmsh6b7540131d4bd54p174e78jsn72bcb2e12075", "x-rapidapi-host: tennis-live-data.p.rapidapi.com")
    suspend fun getAtpMenSinglesRanking() : Response<AtpRanking>
}