package com.onew.onewday.quote

import com.onew.onewday.retrofit.QuoteService
import com.onew.onewday.retrofit.RetrofitApi

class QuoteRepository {
   private val retrofitApi : RetrofitApi = QuoteService.getQuoteService()

   suspend fun getQuote(key : String) = retrofitApi.getQuote(key)
}