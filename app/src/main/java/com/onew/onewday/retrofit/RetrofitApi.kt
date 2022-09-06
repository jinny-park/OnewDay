package com.onew.onewday.retrofit

import com.google.gson.JsonArray
import com.onew.onewday.quote.QuoteModel
import retrofit2.Call
import retrofit2.http.*
import java.nio.channels.spi.AbstractSelectionKey

interface RetrofitApi {


    @GET("request/helpful_text")
    fun getQuote(@Query("apikey") key: String) : Call<JsonArray>
}