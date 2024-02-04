package com.example.homework21.presenter.screen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework21.databinding.ItemShmotkiRecyclerBinding
import com.example.homework21.presenter.model.ShmotkebisUI

class ShmotkiRecyclerAdapter  : ListAdapter<ShmotkebisUI, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShmotkebisUI>() {
            override fun areItemsTheSame(oldItem: ShmotkebisUI, newItem: ShmotkebisUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShmotkebisUI, newItem: ShmotkebisUI): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ShmotkisViewHolder(private val binding: ItemShmotkiRecyclerBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(postion:Int) {
            val product = currentList[postion]

            binding.apply {
                Glide.with(itemView.context)
                    .load(product.cover)
                    .into(imgView)
                tvName.text = product.title
                tvPrise.text = product.price
                if(!product.favorite) imageButton.visibility = View.GONE
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShmotkisViewHolder {
        return ShmotkisViewHolder(ItemShmotkiRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ShmotkisViewHolder)holder.bind(position)
    }


}