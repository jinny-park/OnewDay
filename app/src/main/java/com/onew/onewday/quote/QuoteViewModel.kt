package com.onew.onewday.quote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.onew.onewday.retrofit.QuoteService
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONTokener

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _getQuoteResponse = MutableLiveData<JsonArray>()

    val getQuoteResponse : LiveData<JsonArray> = _getQuoteResponse

    fun getQuote(key: String){
        viewModelScope.launch {
            val response = repository.getQuote(key)

            if(response.isSuccessful){
                _getQuoteResponse.postValue(response.body())
                Log.d("QuoteFragment", _getQuoteResponse.toString());

            }else{

            }
        }
    }

}