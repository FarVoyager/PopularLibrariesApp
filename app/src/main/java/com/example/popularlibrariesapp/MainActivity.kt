package com.example.popularlibrariesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.presenter.CountersPresenter
import com.example.popularlibrariesapp.recyclerView.UsersRecyclerViewAdapter
import com.example.popularlibrariesapp.view.CountersView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), CountersView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { CountersPresenter(GitHubUsersRepo()) }
    private var adapter: UsersRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter)
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


}