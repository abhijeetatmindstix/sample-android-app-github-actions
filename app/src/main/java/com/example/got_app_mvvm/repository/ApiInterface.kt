package com.example.got_app_mvvm.repository

import com.example.got_app_mvvm.model.DataItem
import retrofit2.Call
import retrofit2.http.GET

/**
 * interface to denote endpoints.
 */
interface ApiInterface {
    @GET("Characters")
    fun getItems(): Call<List<DataItem>>
}