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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.adapter.FriendsAdapter
import ru.leonuraltsev.vk_sdk_app.models.VKUser
import ru.leonuraltsev.vk_sdk_app.presenters.FriendsPresenter
import ru.leonuraltsev.vk_sdk_app.views.FriendsView


class FriendsActivity : MvpAppCompatActivity(),FriendsView {

    private lateinit var rvFriends : RecyclerView
    private lateinit var tvFriendsNoItem : TextView
    private lateinit var cpvFriends : CircularProgressView
    private lateinit var mAdapter : FriendsAdapter

    private lateinit var toolbar: Toolbar


    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        rvFriends = findViewById(R.id.rv_friends)
        tvFriendsNoItem = findViewById(R.id.tv_friends_no_item)
        cpvFriends = findViewById(R.id.cpv_friend)


        friendsPresenter.loadFriends()

        mAdapter = FriendsAdapter()

        rvFriends.adapter = mAdapter
        rvFriends.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
        rvFriends.setHasFixedSize(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_friends,menu)
        var mSearchItem: MenuItem = menu!!.findItem(R.id.action_search)
        mSearchItem.setOnActionExpandListener(object  : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                val searchView : SearchView = mSearchItem.actionView as SearchView
                searchView.queryHint = getString(R.string.friends_search_hint)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        mAdapter.filter(query.toString())
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        mAdapter.filter(newText.toString())
                        return true
                    }

                }
                )
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                return true
            }

        })

        return true
    }



    //Friends View Impl

    override fun startLoading() {
        rvFriends.visibility = View.GONE
        tvFriendsNoItem.visibility = View.GONE
        cpvFriends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvFriends.visibility = View.GONE
    }

    override fun showError(textResource:Int) {
        tvFriendsNoItem.text = getString(textResource)
    }

    override fun setupEmptyList() {
        rvFriends.visibility = View.GONE
        tvFriendsNoItem.visibility = View.VISIBLE

    }

    override fun setupFriendsList(friendsList: ArrayList<VKUser>) {
        rvFriends.visibility = View.VISIBLE
        tvFriendsNoItem.visibility = View.GONE
        mAdapter.setupFriends(friendsList = friendsList)
    }
}
