package ru.leonuraltsev.vk_sdk_app.providers

import android.os.Handler
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

import ru.leonuraltsev.vk_sdk_app.models.VKFriendsRequest
import ru.leonuraltsev.vk_sdk_app.models.VKUser
import ru.leonuraltsev.vk_sdk_app.presenters.FriendsFragmentPresenter
import ru.leonuraltsev.vk_sdk_app.presenters.FriendsPresenter


class FriendsProvider(var presenter : FriendsFragmentPresenter) {

    fun loadFriends(){
        VK.execute(VKFriendsRequest(),object : VKApiCallback<List<VKUser>>{
            override fun fail(error: VKApiExecutionException) {
                presenter.showError(error)
            }

            override fun success(result: List<VKUser>) {
                presenter.friendsLoaded(result as ArrayList<VKUser>)
            }

        })
    }



}