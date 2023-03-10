package com.example.got_app_mvvm.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.got_app_mvvm.R
import com.example.got_app_mvvm.databinding.ItemDisplayBinding
import com.example.got_app_mvvm.model.DataItem
import java.util.*
import kotlin.collections.ArrayList

class DataAdapter(private val context: Context, private val searchView: androidx.appcompat.widget.SearchView) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    //data that we are receiving
    private var characters: ArrayList<DataItem>? = null
    //filtered data used for search purpose
    private var filteredCharacters: ArrayList<DataItem>? = null

    var onItemClick : ((DataItem) -> Unit)? = null


    //view holder class
    inner class MyViewHolder(val binding: ItemDisplayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDisplayBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            characterFirstName.text = characters?.get(position)?.firstName
            characterFullName.text = characters?.get(position)?.fullName
            characterTitle.text = characters?.get(position)?.title
            Glide.with(context) // use the context of the parent view
                .load(characters?.get(position)?.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .circleCrop()
                .into(imageCharacter)
        }
        holder.itemView.setOnClickListener{
            characters?.get(position)?.let { it1 -> onItemClick?.invoke(it1) }
        }
    }

    override fun getItemCount(): Int {
        return characters?.size ?: 0
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(characters: ArrayList<DataItem>?) {
        this.characters = characters
        notifyDataSetChanged()
    }

    /**
     * Filter for searching the characters
     * @param String name of character is being passed.
     * If string is matched it will update it in filtered list.
     * Otherwise data will be in original characters
     * @return null
     */
    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String?) {
        filteredCharacters = ArrayList<DataItem>()
        if (!query.isNullOrEmpty()) {
            val lowerCaseQuery = query.lowercase(Locale.ROOT)
            characters?.forEach { item ->
                if (item.firstName?.lowercase(Locale.ROOT)?.contains(lowerCaseQuery) == true) {
                    filteredCharacters?.add(item)
                }
            }
            setData(filteredCharacters)
            notifyDataSetChanged()
        } else if (!searchView.isFocused) {
            setData(characters)
            notifyDataSetChanged()
        }
    }


}