package com.example.apexify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.apexify.R
import com.example.apexify.databinding.FragmentStatsBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class StatsFragment : Fragment(R.layout.fragment_stats) {

    private var _binding  : FragmentStatsBinding? = null
    private val binding get() = _binding!!


    private val client = OkHttpClient()
    private var auth = "19319beea4f9eb0f896dd0d5caa1793e"

    private fun fetchAcc(username:String)
    {
        val url = URL("https://api.mozambiquehe.re/bridge?auth=$auth&player=$username&platform=PC")
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
                println("error")
            }

            override fun onResponse(call: Call, response: Response) {

                        try {
                            val response = response.body?.string()
                            val result = JSONObject(response.toString())
                            val global = result.getJSONObject("global")
                            val level = global.get("level").toString()
                            val name = global.get("name").toString()


                            //Get player rank
                            val r = global.getJSONObject("rank")
                            val rank = r.get("rankName").toString()
                            val rankImg = r.get("rankImg").toString()


                            //Get player kills
                            val total = result.getJSONObject("total")
                            val k = total.getJSONObject("kills")
                            val kills = k.get("value").toString()

                            //Get player status
                            val realtime = result.getJSONObject("realtime")
                            val status = realtime.get("currentStateAsText").toString()




                            //Bundle api values
                            val bundle = Bundle()

                            bundle.putString("Name",name)
                            bundle.putString("Level",level)
                            bundle.putString("Rank",rank)
                            bundle.putString("RankImg",rankImg)
                            bundle.putString("Kills",kills)
                            bundle.putString("Status",status)
                            println(bundle)
                            //Move to detail fragment
                            val fragment = StatsDetailFragment()
                            val fragmentManager = this@StatsFragment.parentFragmentManager
                            fragmentManager.beginTransaction().replace(R.id.layout,fragment).commit()

                            //Pass bundle to detail fragment
                            fragment.arguments = bundle
                        } catch (_: java.lang.Exception) {

                    }

            }
        })

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }



    override fun onCreateView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val input = binding.etPlayerName
        val btnSearch = binding.btnSearch


        btnSearch.setOnClickListener {
            val playerName = input.text.toString();
            fetchAcc(playerName)
        }
        return view
    }
}