package com.onew.onewday.fragment

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.onew.onewday.DiaryList.DiaryListAdapter
import com.onew.onewday.DiaryList.DiaryModel
import com.onew.onewday.R
import com.onew.onewday.databinding.ActivityLoginBinding.inflate
import com.onew.onewday.databinding.FragmentDiaryListBinding
import com.onew.onewday.databinding.FragmentQuoteBinding
import com.onew.onewday.quote.QuoteMarkListAdapter
import com.onew.onewday.quote.QuoteModel
import com.onew.onewday.quote.QuoteRepository
import com.onew.onewday.quote.QuoteViewModel
import com.onew.onewday.retrofit.RetrofitApi
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteFragment : Fragment() {

    private val BASE_URL = "https://api.qwer.pw/"
    private lateinit var quoteText : TextView
    private lateinit var binding: FragmentQuoteBinding
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<QuoteModel>()
    private lateinit var viewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: QuoteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_quote,container,false)
        binding = FragmentQuoteBinding.bind(view)
        quoteText = binding.quote

        recyclerView = binding.nav3Recyclerview
        viewManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        recyclerView.layoutManager = viewManager
        viewAdapter = QuoteMarkListAdapter(dataList)
        dataList.add(QuoteModel("사람이 여행하는 것은 도착하기 위해서가 아닌 여행하기 위해서다. - 괴테"))
        dataList.add(QuoteModel("죽고싶지만 떡볶이는 먹고싶어.- 박의진"))
        dataList.add(QuoteModel("이것은 테스트 명언입니다. - 알파테스트"))
        viewAdapter.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter

        if(dataList.isEmpty()){
            binding.bookmarkLayout.visibility = View.VISIBLE
        }
        repository = QuoteRepository()
        viewModel = QuoteViewModel(repository)
        viewModel.getQuote("guest")
        viewModel.getQuoteResponse.observe(requireActivity(), Observer {
            val jsonArray = JSONTokener(it.toString()).nextValue() as JSONArray
            val quote = jsonArray.getJSONObject(1).getString("respond")
            quoteText.text = quote
            Log.d("QuoteFragment", quote);
        }

        )

//        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()).build();
//        val service = retrofit.create(RetrofitApi::class.java)

//        service.getQuote("guest").enqueue(object : Callback<JsonArray> {
//            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
//                if(response.isSuccessful){
//                    // 정상적으로 통신이 성고된 경우
//                    val jsonArray = JSONTokener(response.body().toString()).nextValue() as JSONArray
//                    val quote = jsonArray.getJSONObject(1).getString("respond")
//                    setQuote(quote)
//                    Log.d("quoteText.GetText",quoteText.text.toString())
//
//                    Log.d("QuoteFragment", response.body().toString());
//                    Log.d("QuoteFragment", quote);
//
//                }else{
//                    Log.d("QuoteFragment",response.message());
//                }
//            }
//
//            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
//                Log.d("YMC", "onFailure 에러: " + t.message.toString());
//            }
//        })


        return view
    }



    private fun setQuote(quote: String){
        binding.quote.text = quote
        Log.d("setquote",quote)
    }

}