package ru.leonuraltsev.vk_sdk_app.presenters

import android.content.Intent
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.views.LoginView


@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess)
                viewState.openFriends()
            else
                viewState.showError(textResource = R.string.login_error_credentials)

        },500)

    }

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?) : Boolean {
        val callback = object : VKAuthCallback{
            override fun onLogin(token: VKAccessToken) {
                viewState.openFriends()
            }

            override fun onLoginFailed(errorCode: Int) {
                viewState.showError(textResource = R.string.login_error_credentials)
            }

        }

        if(data == null || !VK.onActivityResult(requestCode,resultCode,data,callback)){
            return false
        }
        return true
    }
}