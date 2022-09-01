package com.abhi.rxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhi.rxjava.R
import com.abhi.rxjava.databinding.ActivityMainBinding
import com.abhi.rxjava.databinding.RecyclerViewLayoutBinding
import com.abhi.rxjava.model.VolumeInfo
import com.bumptech.glide.Glide

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {
    var bookListData = ArrayList<VolumeInfo>()
    private lateinit var binding :RecyclerViewLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = RecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }


    class MyViewHolder(private val itemBinding: RecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(data: VolumeInfo) {
            itemBinding.tvTitle.text = data.volumeInfo.title
            itemBinding.tvPublisher.text = data.volumeInfo.publisher
            itemBinding.tvDescription.text = data.volumeInfo.description

            val url = data.volumeInfo?.imageLinks?.smallThumbnail/*
                 Glide.with(this)
                     .load(url)
                     .circleCrop()
                     .into(itemBinding.thumbImageView)*/
            if (url !== null) {
                Glide.with(itemBinding.thumbImageView.context)
                    .load(url)
                    .into(itemBinding.thumbImageView)
            } else {
                itemBinding.thumbImageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }
}