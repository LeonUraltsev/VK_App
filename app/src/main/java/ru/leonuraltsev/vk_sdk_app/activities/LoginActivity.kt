package ru.leonuraltsev.vk_sdk_app.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.vk.api.sdk.utils.VKUtils
import com.vk.api.sdk.*
import com.vk.api.sdk.auth.VKScope
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.presenters.LoginPresenter
import ru.leonuraltsev.vk_sdk_app.views.LoginView


class LoginActivity : MvpAppCompatActivity(),LoginView {

    private lateinit var txtLoginHello : TextView
    private lateinit var btnLogin : Button
    private lateinit var cpvLogin : CircularProgressView

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtLoginHello = findViewById(R.id.txt_login_hello)
        btnLogin = findViewById(R.id.btn_login)
        cpvLogin = findViewById(R.id.cpv_login)
        btnLogin.setOnClickListener {
            VK.login(this@LoginActivity, listOf(VKScope.FRIENDS))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!loginPresenter.loginVk(requestCode = requestCode, resultCode = resultCode,data = data)){
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun startLoading() {
        btnLogin.visibility = View.GONE
        cpvLogin.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btnLogin.visibility = View.VISIBLE
        cpvLogin.visibility = View.GONE
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext,FriendsActivity::class.java))
    }

    override fun showError(textResource : Int) {
        Toast.makeText(applicationContext,getString(textResource),Toast.LENGTH_LONG).show()
    }
}
