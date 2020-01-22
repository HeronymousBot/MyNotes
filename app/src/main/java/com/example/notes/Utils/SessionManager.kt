package com.example.notes.Utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    val sharedPreferences : String = "shared_preferences"



    fun clear(context: Context){
        val sharedPrefs : SharedPreferences = context.getSharedPreferences(sharedPreferences, 0)

        val editor: SharedPreferences.Editor = sharedPrefs.edit()

        editor.clear()
        editor.commit()
    }

}