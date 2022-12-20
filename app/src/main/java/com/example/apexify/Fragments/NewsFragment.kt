package com.example.apexify.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.NewsAdapter
import com.example.apexify.R
import com.example.apexify.databinding.FragmentMapBinding
import com.example.apexify.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private var _binding  : FragmentNewsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val values = listOf("Mount Olympus","Storm Point","Kings Canyon","Mount Olympus","Storm Point","Kings Canyon")

        val recyclerView: RecyclerView = binding.rvNews;
        val newsAdapter = NewsAdapter(values)
        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)



    return view;
    }

}