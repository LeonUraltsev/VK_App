package ru.leonuraltsev.vk_sdk_app.helpers

import android.app.Application
import android.content.Intent
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.vk.api.sdk.VKTokenExpiredHandler
import ru.leonuraltsev.vk_sdk_app.activities.LoginActivity


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        VK.addTokenExpiredHandler(tokenTracker)
    }

    private val tokenTracker = object: VKTokenExpiredHandler {

        override fun onTokenExpired() {

        }
    }
//    override fun onCreate() {
//        super.onCreate()
//        VK.initialize(applicationContext)
//    }
}