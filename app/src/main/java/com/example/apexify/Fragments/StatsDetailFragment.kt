package com.example.apexify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apexify.R
import com.example.apexify.databinding.FragmentStatsDetailBinding
import com.squareup.picasso.Picasso


class StatsDetailFragment : Fragment(R.layout.fragment_stats_detail) {

    private var _binding  : FragmentStatsDetailBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsDetailBinding.inflate(layoutInflater, container, false)
        val view = binding!!.root

        val args = this.arguments
        //Get all variables from the Bundle
        val name = args?.getString("Name")
        val level = args?.getString("Level")
        val rank = args?.getString("Rank")
        val rankImg = args?.getString("RankImg")
        val kills = args?.getString("Kills")
        val status = args?.getString("Status")

        //Get all the layout fields
        val tv_name = binding!!.tvAccount
        val tv_rank = binding!!.tvRank
        val img_rank = binding!!.imgRank
        val tv_kills = binding!!.tvKills
        val tv_level = binding!!.tvLevel
        val tv_status = binding!!.tvStatus

        //Set the variables in the fields
        tv_name.text = name
        tv_level.text = "Level: $level"
        Picasso.get().load(rankImg).into(img_rank)
        tv_rank.text = rank
        tv_kills.text = "Kills: $kills"
        tv_status.text = "Status: $status"

        return view
        }
    }
