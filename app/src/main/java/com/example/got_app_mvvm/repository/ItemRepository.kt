package com.example.got_app_mvvm.repository

import com.example.got_app_mvvm.model.DataItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://thronesapi.com/api/v2/"

/**
 *  intermediary between the ViewModel and the data sources (local or remote)
 *  implementing the methods of interface.
 */
class ItemRepository{
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    /**
     * Method to get items
     * @return Call<List<DataItem>>
     */
    fun getItems(): Call<List<DataItem>> {
        return api.getItems()
    }
}