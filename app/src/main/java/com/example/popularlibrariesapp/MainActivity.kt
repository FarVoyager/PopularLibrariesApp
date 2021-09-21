package com.example.popularlibrariesapp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.presenter.CountersPresenter
import com.example.popularlibrariesapp.recyclerView.UsersRecyclerViewAdapter
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.CountersView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), CountersView {

    private var binding: ActivityMainBinding? = null
    val navigator = AppNavigator(this,R.id.container)
    private val presenter by moxyPresenter { CountersPresenter(App.instance.router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach{
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}