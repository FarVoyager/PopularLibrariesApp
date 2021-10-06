package com.example.popularlibrariesapp

import android.os.Bundle
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.di.AbsActivity
import com.example.popularlibrariesapp.presenter.MainPresenter
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.MainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : AbsActivity(), MainView {


    private var binding: ActivityMainBinding? = null
    private val navigator = AppNavigator(this,R.id.container)
    private val presenter by moxyPresenter { MainPresenter(router, androidScreens) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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