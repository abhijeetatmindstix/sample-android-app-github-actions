package com.example.got_app_mvvm.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.got_app_mvvm.model.DataItem
import com.example.got_app_mvvm.repository.ItemRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ItemViewModel : ViewModel() {
    private val itemRepository: ItemRepository = ItemRepository()
    private val itemsLiveData: MutableLiveData<List<DataItem>> = MutableLiveData()

    /**
     * This method will collect data from API via Item repository and provide to the
     * adapter class.
     * @return itemsLiveData
     */
    fun getItems(): LiveData<List<DataItem>> {
        itemRepository.getItems().enqueue(object : Callback<List<DataItem>> {
            override fun onResponse(
                call: Call<List<DataItem>>,
                response: Response<List<DataItem>>
            ) {
                if (response.isSuccessful) {
                    itemsLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<DataItem>>, t: Throwable) {
                Log.e("ItemViewModel", "Failed to get items", t)
            }
        })
        return itemsLiveData
    }


}