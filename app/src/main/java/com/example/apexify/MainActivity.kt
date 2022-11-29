package com.example.apexify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.apexify.Fragments.MapFragment
import com.example.apexify.Fragments.NewsFragment
import com.example.apexify.Fragments.StatsFragment
import com.example.apexify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Set map and check map icon
        replaceFragment(MapFragment())
        binding.bottomNavigation.menu.findItem(R.id.Map).setChecked(true)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){

                R.id.Map -> replaceFragment(MapFragment())
                R.id.News -> replaceFragment(NewsFragment())
                R.id.Stats -> replaceFragment(StatsFragment())

                else ->{
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}