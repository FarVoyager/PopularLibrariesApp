package com.example.popularlibrariesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.databinding.FragmentInfoBinding
import com.example.popularlibrariesapp.model.network.ApiHolder
import com.example.popularlibrariesapp.model.network.GitHubUser
import com.example.popularlibrariesapp.model.room.Database
import com.example.popularlibrariesapp.model.room.RetrofitGitHubRepositoriesRepo
import com.example.popularlibrariesapp.model.room.cache.RepositoriesCache
import com.example.popularlibrariesapp.model.room.networkStatus.AndroidNetworkStatus
import com.example.popularlibrariesapp.presenter.InfoPresenter
import com.example.popularlibrariesapp.presenter.GITHUB_USER_KEY
import com.example.popularlibrariesapp.recyclerView.reposRecyclerView.ReposRecyclerViewAdapter
import com.example.popularlibrariesapp.view.BackButtonListener
import com.example.popularlibrariesapp.view.InfoView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {

    var binding: FragmentInfoBinding? = null
    private var adapter: ReposRecyclerViewAdapter? = null

    private val presenter by moxyPresenter {
        InfoPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            arguments?.get(GITHUB_USER_KEY) as GitHubUser,
            RetrofitGitHubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RepositoriesCache(Database.getInstance())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInfoBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setLoginHeader()
    }

    override fun setLogin(login: String) {
        binding?.infoLogin?.text = login
    }

    override fun init() {
        binding?.rvUserRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRecyclerViewAdapter(presenter.repoListPresenter)
        binding?.rvUserRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()

}