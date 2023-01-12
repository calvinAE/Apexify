package com.example.apexify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.apexify.fragments.*
import com.example.apexify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navToggle: ActionBarDrawerToggle


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout,fragment)
        fragmentTransaction.commit()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            navToggle=  ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.app_name,R.string.app_name)
            drawerLayout.addDrawerListener(navToggle)
            navToggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            replaceFragment(MapFragment())

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.Map->replaceFragment(MapFragment())
                    R.id.Stats->replaceFragment(StatsFragment())
                    R.id.News->replaceFragment(NewsFragment())
                    R.id.Options->replaceFragment(OptionsFragment())
                    R.id.Store->replaceFragment(StoreFragment())
                    R.id.Loadout->replaceFragment(LoadOutFragment())


            }
                true
        }

        }
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if(navToggle.onOptionsItemSelected(item)){
                true
            }

            return super.onOptionsItemSelected(item)
            }
    }

