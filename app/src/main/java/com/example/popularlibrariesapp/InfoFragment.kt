package com.example.popularlibrariesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibrariesapp.databinding.FragmentInfoBinding
import com.example.popularlibrariesapp.model.GitHubUser
import com.example.popularlibrariesapp.presenter.GITHUB_USER_KEY
import com.example.popularlibrariesapp.presenter.InfoPresenter
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.InfoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {

    private var binding: FragmentInfoBinding? = null
    private val presenter by moxyPresenter { InfoPresenter(App.instance.router, arguments?.getParcelable<GitHubUser>(GITHUB_USER_KEY)) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInfoBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setLoginHeader()
    }

    override fun backPressed() = presenter.backPressed()

    override fun setLogin(login: String) {
        binding?.infoLogin?.text = login
    }

    companion object {
        fun newInstance(user: GitHubUser): Fragment {
            val fragment = InfoFragment()
            val bundle = Bundle()
            bundle.putParcelable(GITHUB_USER_KEY, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}