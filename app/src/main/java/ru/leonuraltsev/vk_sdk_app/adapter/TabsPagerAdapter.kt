package ru.leonuraltsev.vk_sdk_app.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.leonuraltsev.vk_sdk_app.fragments.FriendsFragment
import ru.leonuraltsev.vk_sdk_app.fragments.FriendsOnlineFragment

class TabsPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    private var tabs : ArrayList<String> = ArrayList()

    init {
        tabs.add("Друзья")
        tabs.add("Онлайн")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FriendsFragment.getInstance()
            1 -> return FriendsOnlineFragment.getInstance()
        }
        Log.e("TAb",tabs[0])
        return FriendsFragment.getInstance()
    }

    override fun getCount(): Int {
        return tabs.size
    }
}