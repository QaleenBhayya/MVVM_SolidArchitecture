package com.startzplay.network


import com.startzplay.data.remote.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiEndPoint.MULTI_SEARCH)
    suspend fun multiSearch(
        @Query("query")query:String,
        @Query("include_adult")include_adult:Boolean,
        @Query("language")language:String,
        @Query("page")page:Int
    ): Response<SearchResponse>

}