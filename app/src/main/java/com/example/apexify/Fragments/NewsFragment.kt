package com.example.apexify.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.ApiInterface
import com.example.apexify.Model.News
import com.example.apexify.Model.NewsItem
import com.example.apexify.NewsAdapter
import com.example.apexify.R
import com.example.apexify.databinding.FragmentNewsBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment(R.layout.fragment_news) {

    private var _binding  : FragmentNewsBinding? = null
    private val binding get() = _binding!!

    lateinit var newsList: ArrayList<NewsItem>


    private var auth = "19319beea4f9eb0f896dd0d5caa1793e"


    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = binding.rvNews
        newsList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        val newsAdapter = NewsAdapter(newsList)



        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.mozambiquehe.re/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface:ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<News> = apiInterface.fetchNews()

        call.enqueue(object : Callback<News?>{
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                    newsList.clear()
                    for(news in response.body()!!){
                        newsList.add(news)
                    }
                    newsAdapter.notifyDataSetChanged()

                recyclerView.adapter = newsAdapter

            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                println(t)
            }

        })

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }




}