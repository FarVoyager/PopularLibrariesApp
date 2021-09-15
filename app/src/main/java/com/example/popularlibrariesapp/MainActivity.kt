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

        val clickListener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        binding?.btnCounter1?.setOnClickListener(clickListener)
        binding?.btnCounter2?.setOnClickListener(clickListener)
        binding?.btnCounter3?.setOnClickListener(clickListener)
    }

        override fun setBtnText(index: Int, text: String) {
        when (index) {
            0 -> binding?.btnCounter1?.text = text
            1 -> binding?.btnCounter2?.text = text
            2 -> binding?.btnCounter3?.text = text
        }
    }
}