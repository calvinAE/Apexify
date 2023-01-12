package com.example.apexify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apexify.ApexApplication
import com.example.apexify.LoadOutViewModel
import com.example.apexify.R
import com.example.apexify.WordViewModelFactory
import com.example.apexify.adapters.LoadOutAdapter
import com.example.apexify.database.LoadOutRoomDatabase
import com.example.apexify.databinding.FragmentLoadoutsBinding
import kotlinx.coroutines.launch

class LoadOutFragment: Fragment(R.layout.fragment_loadouts)  {
    private var _binding  : FragmentLoadoutsBinding? = null
    private val binding get() = _binding!!


    private val loadOutViewModel: LoadOutViewModel by viewModels {
        WordViewModelFactory(((activity?.application) as ApexApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadoutsBinding.inflate(inflater, container, false)
        val view = binding.root
        val btnNew = binding.btnNew
        val rv = binding.rvLoadouts

        val adapter = LoadOutAdapter()
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)


        loadOutViewModel.allLoaduts.observe(viewLifecycleOwner) { loadouts ->
            loadouts.let { adapter.submitList(it) }
        }

        btnNew.setOnClickListener {

            //Move to add loadout fragment
            val fragment = LoadOutAddFragment()
            val fragmentManager = this@LoadOutFragment.parentFragmentManager
            fragmentManager.beginTransaction().add(R.id.layout,fragment).commit()
        }

        return view
    }



}