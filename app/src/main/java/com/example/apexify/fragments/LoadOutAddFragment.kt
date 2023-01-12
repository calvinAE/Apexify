package com.example.apexify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.apexify.Model.LoadOut
import com.example.apexify.R
import com.example.apexify.database.LoadOutRoomDatabase
import com.example.apexify.databinding.FragmentLoadoutAddBinding
import kotlinx.coroutines.launch


class LoadOutAddFragment: Fragment(R.layout.fragment_loadouts) {

    private var _binding  : FragmentLoadoutAddBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadoutAddBinding.inflate(inflater, container, false)
        val view = binding.root
        val weapons = resources.getStringArray(R.array.weapons)
        val btnSave = binding.btnSave


        val adapter = ArrayAdapter<String>(view.context,android.R.layout.simple_spinner_dropdown_item,weapons)
        binding.spPrimary.adapter = adapter
        binding.spSecondary.adapter = adapter

        btnSave.setOnClickListener {
            lifecycleScope.launch {
                addLoadOut()
            }

        }
        return view
    }

    suspend fun addLoadOut(){
        val name = binding.etName.text.toString()
        val primary = binding.spPrimary.selectedItem.toString()
        val secondary = binding.spSecondary.selectedItem.toString()

        val loadOut = LoadOut(name = name, primaryWeapon = primary, secondaryWeapon = secondary)
        LoadOutRoomDatabase.getDatabase(this.requireContext(), scope = lifecycleScope).loadOutDao().addLoadOut(loadOut)
    }
}