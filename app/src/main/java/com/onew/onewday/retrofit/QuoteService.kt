package com.onew.onewday.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteService {

    private val BASE_URL: String  = "https://api.qwer.pw/"

    fun getQuoteService() :RetrofitApi{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

}