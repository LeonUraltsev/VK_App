package ru.leonuraltsev.vk_sdk_app.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.leonuraltsev.vk_sdk_app.models.VKUser

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView : MvpView {
    fun startLoading()
    fun endLoading()
    fun initTabs()
    fun initToolbar()
}