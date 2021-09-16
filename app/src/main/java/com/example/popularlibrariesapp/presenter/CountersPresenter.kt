package com.example.popularlibrariesapp.presenter

import com.example.popularlibrariesapp.R
import com.example.popularlibrariesapp.model.CountersModel
import com.example.popularlibrariesapp.view.CountersView


class CountersPresenter(val view: CountersView) {
    val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun counterClick(id: Int){
        when(id){
            R.id.btn_counter1 -> {
                val nextValue = model.next(0)
                view.setBtnText(0, nextValue.toString())
            }
            R.id.btn_counter2 -> {
                val nextValue = model.next(1)
                view.setBtnText(1, nextValue.toString())
            }
            R.id.btn_counter3 -> {
                val nextValue = model.next(2)
                view.setBtnText(2, nextValue.toString())
            }
        }
    }
}

