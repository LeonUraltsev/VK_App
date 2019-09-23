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
        FriendsProvider(presenter = this).loadFriends()
    }

    fun friendsLoaded(friendsList: ArrayList<VKUser>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.tv_friends_no_item)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }

    fun showError(error: VKApiExecutionException) {
        viewState.showError(R.string.list_error_notification)
    }
}