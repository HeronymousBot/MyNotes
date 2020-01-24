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

    fun saverUserId(userId:String, context : Context){
        val sharedPrefs : SharedPreferences = context.getSharedPreferences(sharedPreferences, 0)
        val editor = sharedPrefs.edit()
        editor.putString("userId", userId)
        editor.apply()
    }

    fun getUserId(context : Context) : Int?{
        val sharedPrefs : SharedPreferences = context.getSharedPreferences(sharedPreferences, 0)
        return sharedPrefs.getString("userId", "0")!!.toInt()
    }

}