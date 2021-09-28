package com.example.popularlibrariesapp.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.databinding.RecyclerItemBinding

class UsersRecyclerViewAdapter(val presenter: IUserListPresenter):
RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val _binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(_binding)
        viewHolder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(viewHolder) }

//        ViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
//            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
//        }
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



    }


}