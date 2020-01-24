package com.example.notes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.notes.Activities.LoginActivity
import com.example.notes.Adapters.MainPageFragmentAdapter
import com.example.notes.Fragments.AlbumsFragment
import com.example.notes.Fragments.EmptyFragment
import com.example.notes.Fragments.PostsFragment
import com.example.notes.Fragments.TodosFragment
import com.example.notes.Utils.SessionManager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val user_data = "shared_preferences"
    var tabLayout: TabLayout? = null
    var toolbar: Toolbar? = null
    var frameLayout: FrameLayout? = null
    var fragmentManager: FragmentManager? = null
    var fragment: Fragment? = null
    var fragmentTransaction: FragmentTransaction? = null
    var userId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = SessionManager().getUserId(applicationContext)

        if (userId == 0) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            setContentView(R.layout.activity_main)
            setComponents()


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu, menu)

        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_icon -> {
                SessionManager().clear(this.applicationContext)
                var toast: Toast =
                    Toast.makeText(this, "Logout done successfully!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setComponents() {
        toolbar = findViewById(R.id.toolbar_MainActivity)
        tabLayout = findViewById(R.id.tabs_mainActivity)
        frameLayout = findViewById(R.id.frameLayout_mainActivity)
        fragment = PostsFragment().newInstance(userId)

        setSupportActionBar(toolbar)

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameLayout_mainActivity, fragment as PostsFragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction!!.commit()
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Posts"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Albums"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Todos"))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                changeFragment(tab!!.position, userId as Int)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {


            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                changeFragment(tab!!.position, userId as Int)
            }

        })
    }

    fun changeFragment(position: Int, userId: Int) {
        if (verifyAvailableNetwork(this)) {
            when (position) {
                0 -> fragment = PostsFragment().newInstance(userId)
                1 -> fragment = AlbumsFragment().newInstance(userId)
                2 -> fragment = TodosFragment().newInstance(userId)

            }
        } else {
            fragment = EmptyFragment()
        }

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.frameLayout_mainActivity, fragment!!)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
