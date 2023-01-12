package com.example.apexify.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.apexify.R
import com.example.apexify.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment(R.layout.fragment_options) {

    private var _binding  : FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    private val languages = arrayOf("English","Dutch")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        val view = binding.root

        val switch = binding.swDarkmode
        val spinner = binding.spLanguages

        //Dark mode switch
       switch.setOnClickListener {
           if(switch.isChecked ){
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
           } else {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
           }
       }

        //Language spinner
        val langAdapter = context?.let { ArrayAdapter<String>(it,android.R.layout.simple_spinner_dropdown_item,languages) }

        spinner.adapter = langAdapter
        return view
    }


}