package com.example.notes.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.notes.Fragments.AlbumsFragment
import com.example.notes.Fragments.EmptyFragment
import com.example.notes.Fragments.PostsFragment
import com.example.notes.Fragments.TodosFragment

class MainPageFragmentAdapter(private val context: Context, fm: FragmentManager, internal var totalTabs: Int) :
    FragmentPagerAdapter(fm, totalTabs) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return PostsFragment()
            }

            1 -> {
                return AlbumsFragment()
            }
            2 -> {
                return TodosFragment()
            }

        }

        return EmptyFragment()
    }

    override fun getCount(): Int {
        return totalTabs
    }
}