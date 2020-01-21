package com.example.notes.Services

import android.content.Context
import com.example.notes.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
        fun getRetrofitInstance(context : Context) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}