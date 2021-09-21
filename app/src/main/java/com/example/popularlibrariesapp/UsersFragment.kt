package com.example.popularlibrariesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.databinding.FragmentUsersBinding
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.presenter.CountersPresenter
import com.example.popularlibrariesapp.presenter.UsersPresenter
import com.example.popularlibrariesapp.recyclerView.UsersRecyclerViewAdapter
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.CountersView
import com.example.popularlibrariesapp.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var binding: FragmentUsersBinding? = null
    private val presenter by moxyPresenter { UsersPresenter(GitHubUsersRepo(), App.instance.router) }
    private var adapter: UsersRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersBinding.inflate(inflater, container, false)
        .also{ binding = it }.root

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter)
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()

}