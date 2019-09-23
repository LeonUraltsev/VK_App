package ru.leonuraltsev.vk_sdk_app.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.vk.api.sdk.VK

import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.adapter.FriendsAdapter
import ru.leonuraltsev.vk_sdk_app.models.VKUser
import ru.leonuraltsev.vk_sdk_app.presenters.FriendsFragmentPresenter
import ru.leonuraltsev.vk_sdk_app.views.FriendsFragmentView


class FriendsFragment : MvpAppCompatFragment(),FriendsFragmentView {

    private lateinit var rvFriends : RecyclerView
    private lateinit var mAdapter : FriendsAdapter
    private lateinit var tvFriendsNoItem : TextView
    private lateinit var cpvFriends : CircularProgressView



    @InjectPresenter
    lateinit var friendsFragmentPresenter:FriendsFragmentPresenter
    companion object{

        fun getInstance() : FriendsFragment{
            val args = Bundle()
            val fragment = FriendsFragment()
            fragment.arguments = args
            return fragment
        }


    }

 override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     var view = inflater.inflate(R.layout.fragment_friends, container, false)
     rvFriends = view.findViewById(R.id.rv_friends)
     mAdapter = FriendsAdapter()
     tvFriendsNoItem = view.findViewById(R.id.tv_friends_no_item)
     cpvFriends = view.findViewById(R.id.cpv_friend)
     rvFriends.adapter = mAdapter
     rvFriends.layoutManager = LinearLayoutManager(view.context.applicationContext,RecyclerView.VERTICAL,false)
     rvFriends.setHasFixedSize(true)

     friendsFragmentPresenter.loadFriends()
     setHasOptionsMenu(true)
     return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        var mSearchItem: MenuItem = menu.findItem(R.id.action_search)
        mSearchItem.setOnActionExpandListener(object  : MenuItem.OnActionExpandListener {
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

                })
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
               return true
            }

        })
    }

    override fun startLoading() {
        rvFriends.visibility = View.GONE
        tvFriendsNoItem.visibility = View.GONE
        cpvFriends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvFriends.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
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
