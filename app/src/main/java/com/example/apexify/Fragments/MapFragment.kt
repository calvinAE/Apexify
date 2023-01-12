package com.example.apexify.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apexify.R
import com.example.apexify.databinding.FragmentMapBinding
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class MapFragment : Fragment(R.layout.fragment_map) {

    private var _binding  : FragmentMapBinding? = null
    private val binding get() = _binding!!



    private val client = OkHttpClient()
    var auth = "19319beea4f9eb0f896dd0d5caa1793e"
    val url = URL("https://api.mozambiquehe.re/maprotation?auth=$auth"  )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                fetchMap(url)
                mainHandler.postDelayed(this, (1 * 60 * 1000).toLong())
            }
        })
        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun fetchMap(url: URL)
    {
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    binding.tvMapTitle.text = e.message
                }

                override fun onResponse(call: Call, response: Response) {

                        activity?.runOnUiThread() {
                            kotlin.run {
                                try {
                                    val result = response.body?.string()
                                    val Jobject = JSONObject(result.toString())

                                    val arrayCurrent = Jobject.getJSONObject("current")

                                    val map = arrayCurrent.get("map").toString()
                                    val timeRemaining = arrayCurrent.get("remainingMins").toString()
                                    val imgUrl = arrayCurrent.get("asset").toString()
                                    val tvText = "Minutes left: $timeRemaining min"


                                    binding.tvMapTitle.text = map
                                    binding.tvRemainigTime.text = tvText
                                    Picasso.get().load(imgUrl).into(binding.imgBackground)

                                } catch (_: java.lang.Exception) {
                                }
                            }
                    }
                }
            })

        }
    }



