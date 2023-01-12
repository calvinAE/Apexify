package com.example.apexify.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.ApiInterface
import com.example.apexify.Model.Store
import com.example.apexify.Model.StoreItem
import com.example.apexify.R
import com.example.apexify.adapters.StoreAdapter
import com.example.apexify.databinding.FragmentStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreFragment: Fragment(R.layout.fragment_store) {
    private var _binding  : FragmentStoreBinding? = null
    private val binding get() = _binding!!

    lateinit var storeList: ArrayList<StoreItem>


    private var auth = "19319beea4f9eb0f896dd0d5caa1793e"

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = binding.rvStore
        storeList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        val storeAdapter = StoreAdapter(storeList)


        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.mozambiquehe.re/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<Store> = apiInterface.fetchStore()

        call.enqueue(object : Callback<Store?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Store?>, response: Response<Store?>) {
                storeList.clear()
                for(news in response.body()!!){
                    storeList.add(news)
                }
                storeAdapter.notifyDataSetChanged()

                recyclerView.adapter = storeAdapter

            }

            override fun onFailure(call: Call<Store?>, t: Throwable) {
              println(t)
            }
        })
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}