package com.example.popularlibrariesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.presenter.CountersPresenter
import com.example.popularlibrariesapp.view.CountersView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), CountersView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { CountersPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnCounter1?.setOnClickListener { presenter.counterClickOne() }
        binding?.btnCounter2?.setOnClickListener { presenter.counterClickTwo() }
        binding?.btnCounter3?.setOnClickListener { presenter.counterClickThree() }
    }

    override fun setBtnOneText(text: String) { binding?.btnCounter1?.text = text }
    override fun setBtnTwoText(text: String) { binding?.btnCounter2?.text = text }
    override fun setBtnThreeText(text: String) { binding?.btnCounter3?.text = text }
}