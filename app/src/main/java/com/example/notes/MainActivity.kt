package com.example.notes

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.notes.Activities.LoginActivity
import com.example.notes.Adapters.MainPageFragmentAdapter
import com.example.notes.Fragments.AlbumsFragment
import com.example.notes.Fragments.PostsFragment
import com.example.notes.Fragments.TodosFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val user_data = "user_data"
    var tabLayout: TabLayout? = null
    var frameLayout: FrameLayout? = null
    var fragmentManager: FragmentManager? = null
    var fragment: Fragment? = null
    var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences = getSharedPreferences(user_data, PRIVATE_MODE)

        if (sharedPref.getInt("userId", 1) == 0) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            setContentView(R.layout.activity_main)
            tabLayout = findViewById(R.id.tabs_mainActivity)
            frameLayout = findViewById(R.id.frameLayout_mainActivity)
            fragment  = PostsFragment().newInstance(1)
            fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction!!.replace(R.id.frameLayout_mainActivity, fragment)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction!!.commit()

            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    when (tab!!.position) {
                        0 -> fragment = PostsFragment().newInstance(1)
                        1 -> fragment = AlbumsFragment().newInstance(1)
                        2 -> fragment = TodosFragment().newInstance(1)

                    }
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.frameLayout_mainActivity, fragment)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ft.commit()
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onTabSelected(p0: TabLayout.Tab?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }

    }
}
