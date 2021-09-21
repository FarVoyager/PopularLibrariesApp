package com.example.popularlibrariesapp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountersView: MvpView {
    fun init()
    fun updateList()

}