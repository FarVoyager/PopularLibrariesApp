package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.view.CountersView

class CountersPresenter(private val view: CountersView) {

    private val model = CountersModel()

    fun counterClickOne() {
        val nextValue = model.next(0)
        view.setBtnOneText(nextValue.toString())
    }
    fun counterClickTwo() {
        val nextValue = model.next(1)
        view.setBtnTwoText(nextValue.toString())
    }
    fun counterClickThree() {
        val nextValue = model.next(2)
        view.setBtnThreeText(nextValue.toString())
    }

}