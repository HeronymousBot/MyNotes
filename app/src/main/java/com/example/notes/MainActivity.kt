package com.example.notes

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.notes.Activities.LoginActivity
import com.example.notes.Adapters.MainPageFragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val user_data = "user_data"
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences = getSharedPreferences(user_data, PRIVATE_MODE)

        if (sharedPref.getInt("userId", 0) == 0) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            setContentView(R.layout.activity_main)
            tabLayout = findViewById(R.id.tabs_mainActivity)
            viewPager = findViewById(R.id.viewPager_mainActivity)

            tabLayout!!.addTab(tabLayout!!.newTab().setText("Posts"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Albums"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Todos"))

            val adapter =
                MainPageFragmentAdapter(this, supportFragmentManager, tabLayout!!.tabCount)

            viewPager!!.adapter = adapter

            viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewPager!!.currentItem = tab!!.position
                }

            })

        }
    }
}
