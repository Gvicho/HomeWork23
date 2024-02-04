package com.example.homework21.presenter.screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.homework21.R
import com.example.homework21.databinding.ItemCategoryBinding

class CategorysRecyclerAdapter (private var data:List<String>,private val listener:CallBack,private var lastSelectedItem:Int) : RecyclerView.Adapter<CategorysRecyclerAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.itemBtn.text = data[position]
            if(lastSelectedItem == position)selectedBg()
            else defaultBg()
            bindListener(position)
        }

        private fun bindListener(position: Int){
            binding.itemBtn.setOnClickListener {
                if(lastSelectedItem!=position) {
                    val oldItem = lastSelectedItem
                    lastSelectedItem = position
                    notifyItemChanged(oldItem)
                    notifyItemChanged(lastSelectedItem)
                }
                listener.filterShmotkebi(data[position],position)
            }
        }

        private fun defaultBg() {
            binding.itemBtn.background = AppCompatResources.getDrawable(itemView.context,R.drawable.item_category_shape)
        }

        private fun selectedBg() {
            binding.itemBtn.background = AppCompatResources.getDrawable(itemView.context,R.drawable.item_category_selected_shape)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(position)
    }
}

interface CallBack{
    fun filterShmotkebi(category:String,lastSelectedCategory: Int)
}