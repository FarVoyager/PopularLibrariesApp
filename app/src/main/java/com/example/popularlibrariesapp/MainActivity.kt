package com.example.popularlibrariesapp

import android.os.Bundle
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.presenter.MainPresenter
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.MainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    @Inject lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this,R.id.container)

    private val presenter by moxyPresenter { MainPresenter().apply { App.instance.appComponent.inject(this) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        App.instance.appComponent.inject(this)
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