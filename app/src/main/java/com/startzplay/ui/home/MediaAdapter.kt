package com.startzplay.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.startzplay.data.remote.search.SearchItem
import com.startzplay.databinding.ItemMediaBinding
import com.startzplay.utils.Constants
import com.startzplay.utils.setImageFromUrl

class MediaAdapter(
    private val mContext: Context, private val mData: MutableList<SearchItem>,
    private val onItemClick: (item: SearchItem) -> Unit
) :
    RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = ItemMediaBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.setData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class MediaViewHolder(var viewBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun setData(mData: SearchItem) {
            viewBinding.apply {
                mediaTvName.text = mData.title ?: mData.name ?: "Name is missing"
                mData.poster_path?.apply {
                    mediaIvPoster.setImageFromUrl(Constants.IMAGE_BASE_URL +mData.poster_path )
                }

            }
            viewBinding.root.setOnClickListener {
                onItemClick(mData)
            }
        }
    }


}