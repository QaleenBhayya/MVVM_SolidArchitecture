package com.startzplay.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startzplay.data.local.allCategory.AllCategory
import com.startzplay.data.remote.search.SearchItem
import com.startzplay.databinding.ItemCategoryBinding

class HomeAdapter(
    private val mContext: Context,
    private val onItemClick: (item: SearchItem) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.StopsHolder>() {

    private val mData = ArrayList<AllCategory>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopsHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return StopsHolder(view)
    }

    override fun onBindViewHolder(holder: StopsHolder, position: Int) {
        holder.setData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class StopsHolder(var viewBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun setData(mData: AllCategory) {
            viewBinding.apply {
                categoryTvName.text = mData.name
                categoryRvMedia.adapter = setAdapter(mData)
            }
        }
    }

    fun addData(data:List<AllCategory>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    private fun setAdapter(item: AllCategory): MediaAdapter {
        return MediaAdapter(mContext, item.medias as ArrayList, onItemClick)
    }
}