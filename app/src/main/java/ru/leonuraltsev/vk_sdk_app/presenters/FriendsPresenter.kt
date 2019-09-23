package ru.leonuraltsev.vk_sdk_app.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.exceptions.VKApiExecutionException
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.models.VKUser
import ru.leonuraltsev.vk_sdk_app.providers.FriendsProvider
import ru.leonuraltsev.vk_sdk_app.views.FriendsView


@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        viewState.initTabs()
        viewState.initToolbar()
    }

}