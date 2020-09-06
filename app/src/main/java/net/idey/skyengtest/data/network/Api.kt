package net.idey.skyengtest.data.network

import net.idey.skyengtest.data.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("words/search")
    suspend fun searchWord(@Query("search") query: String): List<SearchResult>

}