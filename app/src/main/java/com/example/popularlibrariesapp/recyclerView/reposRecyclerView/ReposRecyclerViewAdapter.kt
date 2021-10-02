package com.example.popularlibrariesapp.recyclerView.reposRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.databinding.RecyclerItemRepoBinding

class ReposRecyclerViewAdapter(val presenter: IRepoListPresenter): RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolderRepo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepo {
        val binding = RecyclerItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderRepo(binding)
        viewHolder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(viewHolder)}
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderRepo, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolderRepo(private val binding: RecyclerItemRepoBinding): RecyclerView.ViewHolder(binding.root),
            RepoItemView {
        override var pos = -1
        override fun setRepoName(text: String) {
            binding.rvRepoName.text = text
        }
    }
}