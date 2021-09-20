package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.view.CountersView
import moxy.InjectViewState
import moxy.MvpPresenter

class CountersPresenter(private val model: CountersModel): MvpPresenter<CountersView>() {

    fun counterClickOne() {
        val nextValue = model.next(0)
        viewState.setBtnOneText(nextValue.toString())
    }
    fun counterClickTwo() {
        val nextValue = model.next(1)
        viewState.setBtnTwoText(nextValue.toString())
    }
    fun counterClickThree() {
        val nextValue = model.next(2)
        viewState.setBtnThreeText(nextValue.toString())
    }

}