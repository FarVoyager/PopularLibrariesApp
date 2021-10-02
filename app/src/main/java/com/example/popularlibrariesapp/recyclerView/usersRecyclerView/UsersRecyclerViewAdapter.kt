package com.example.popularlibrariesapp.recyclerView.usersRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.databinding.RecyclerItemUserBinding
import com.example.popularlibrariesapp.view.glide.IImageLoader

class UsersRecyclerViewAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>):
RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolderUser>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val binding = RecyclerItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderUser(binding)
        viewHolder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(viewHolder) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolderUser(private val binding: RecyclerItemUserBinding): RecyclerView.ViewHolder(binding.root),
        UserItemView {
        override var pos = -1
        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, binding.ivAvatar)
        }
    }
}