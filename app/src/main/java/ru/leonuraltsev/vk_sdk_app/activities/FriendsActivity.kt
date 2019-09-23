package ru.leonuraltsev.vk_sdk_app.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.vk.api.sdk.VK
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.adapter.FriendsAdapter
import ru.leonuraltsev.vk_sdk_app.adapter.TabsPagerAdapter
import ru.leonuraltsev.vk_sdk_app.fragments.FriendsFragment
import ru.leonuraltsev.vk_sdk_app.models.VKUser
import ru.leonuraltsev.vk_sdk_app.presenters.FriendsPresenter
import ru.leonuraltsev.vk_sdk_app.views.FriendsView


class FriendsActivity : MvpAppCompatActivity(),FriendsView {
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager:ViewPager

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        friendsPresenter.loadFriends()
    }
    override fun initTabs() {
        viewPager = findViewById(R.id.view_pager_friends)
        tabLayout = findViewById(R.id.tab_friends_layout)
        val pagerAdapter = TabsPagerAdapter(supportFragmentManager,behavior = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "VKFriends"
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_friends,menu)
        return true
    }

    //Friends View Impl

    override fun startLoading() {

    }

    override fun endLoading() {

    }

}
