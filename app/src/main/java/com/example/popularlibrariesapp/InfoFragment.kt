package com.example.popularlibrariesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibrariesapp.databinding.FragmentInfoBinding
import com.example.popularlibrariesapp.databinding.FragmentUsersBinding
import com.example.popularlibrariesapp.model.GitHubUsersRepo
import com.example.popularlibrariesapp.presenter.InfoPresenter
import com.example.popularlibrariesapp.presenter.LOGIN_KEY
import com.example.popularlibrariesapp.presenter.UsersPresenter
import com.example.popularlibrariesapp.screens.AndroidScreens
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.InfoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import kotlin.math.log

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {

    companion object {
        fun newInstance(bundle: Bundle): InfoFragment {
           val fragment = InfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    var binding: FragmentInfoBinding? = null
    private val presenter by moxyPresenter { InfoPresenter(App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInfoBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLogin()
    }

    private fun setLogin() {
        val login: String = this.arguments?.get(LOGIN_KEY) as String
        presenter.setLoginHeader(login)
    }

    override fun backPressed() = presenter.backPressed()

    override fun setLogin(login: String) {
        binding?.infoLogin?.text = login
    }
}