package com.example.popularlibrariesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.databinding.FragmentUsersBinding
import com.example.popularlibrariesapp.model.network.ApiHolder
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RetrofitGitHubUsersRepo
import com.example.popularlibrariesapp.model.room.cache.UsersCache
import com.example.popularlibrariesapp.model.room.networkStatus.AndroidNetworkStatus
import com.example.popularlibrariesapp.presenter.UsersPresenter
import com.example.popularlibrariesapp.recyclerView.usersRecyclerView.UsersRecyclerViewAdapter
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.UsersView
import com.example.popularlibrariesapp.view.glide.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object { fun newInstance() = UsersFragment() }
    private var binding: FragmentUsersBinding? = null
    private val presenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread(),
            RetrofitGitHubUsersRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), UsersCache()),
            App.instance.router,
            AndroidScreens())
    }
    private var adapter: UsersRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersBinding.inflate(inflater, container, false)
        .also{ binding = it }.root

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter, GlideImageLoader())
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