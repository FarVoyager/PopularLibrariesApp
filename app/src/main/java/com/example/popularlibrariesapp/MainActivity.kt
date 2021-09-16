package com.example.popularlibrariesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.presenter.CountersPresenter
import com.example.popularlibrariesapp.view.CountersView

class MainActivity : AppCompatActivity(), CountersView {

    private var binding: ActivityMainBinding? = null
    private val presenter = CountersPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val clickListenerBtn1 = View.OnClickListener { presenter.counterClickOne() }
        val clickListenerBtn2 = View.OnClickListener { presenter.counterClickTwo() }
        val clickListenerBtn3 = View.OnClickListener { presenter.counterClickThree() }

        binding?.btnCounter1?.setOnClickListener(clickListenerBtn1)
        binding?.btnCounter2?.setOnClickListener(clickListenerBtn2)
        binding?.btnCounter3?.setOnClickListener(clickListenerBtn3)
    }

    override fun setBtnOneText(text: String) { binding?.btnCounter1?.text = text }
    override fun setBtnTwoText(text: String) { binding?.btnCounter2?.text = text }
    override fun setBtnThreeText(text: String) { binding?.btnCounter3?.text = text }
}