package ru.leonuraltsev.vk_sdk_app.models

import android.content.Context
import android.util.Log
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiException
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import java.io.IOException

class VKFriendsRequest(uid:Int = 0) : VKRequest<List<VKUser>>("friends.get"){
    init {
        if (uid != 0) {
            addParam("user_id", uid)
            addParam("lang","ru")
        }
        val photo = "photo_100"
        val city= "city"
        var req: ArrayList<String> = ArrayList()
        req.add(photo)
        req.add(city)
        addParam("fields", req)
        addParam("order","hints")

    }

    override fun parse(response: String): List<VKUser> {
        return super.parse(response)
    }

    override fun onExecute(manager: VKApiManager): List<VKUser> {
        val config = manager.config

        params["lang"] = "ru"
        params["device_id"] = config.deviceId.value
        params["v"] = config.version

        return manager.execute(VKMethodCall.Builder()
            .args(params)
            .method(method)
            .version(config.version)
            .build(),this
        )
    }

    override fun parse(r: JSONObject): List<VKUser> {

        val users = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<VKUser>()
        Log.e("Er",users.toString())
        for (i in 0 until users.length()) {
            result.add(VKUser.parse(users.getJSONObject(i)))
        }

        return result
    }
}