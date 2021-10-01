package com.example.popularlibrariesapp.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.databinding.RecyclerItemBinding
import com.example.popularlibrariesapp.view.glide.IImageLoader

class UsersRecyclerViewAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>):
RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(viewHolder) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root), UserItemView {
        override var pos = -1
        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, binding.ivAvatar)
        }
    }
}