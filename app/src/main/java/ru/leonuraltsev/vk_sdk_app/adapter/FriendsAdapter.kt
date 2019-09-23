package ru.leonuraltsev.vk_sdk_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ru.leonuraltsev.vk_sdk_app.R
import ru.leonuraltsev.vk_sdk_app.models.VKUser

class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFriendsList : ArrayList<VKUser> = ArrayList()
    private var mSourceList : ArrayList<VKUser> = ArrayList()
    fun setupFriends(friendsList: ArrayList<VKUser>){
        mSourceList.clear()
        mSourceList.addAll(friendsList)
        filter(query = "")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        var firstAndSurName : String
        mSourceList.forEach {
            firstAndSurName = it.name + " " + it.surName
            if(it.name.contains(query,ignoreCase = true) || it.surName.contains(query,ignoreCase = true) || firstAndSurName.contains(query,ignoreCase = true)){
                mFriendsList.add(it)
            }else {
                it.city?.let { city ->
                    if(city.contains(query,ignoreCase = true)){
                        mFriendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend,parent,false)
        return FriendsViewHolder(itemView=itemView)
    }

    override fun getItemCount(): Int {
        return mFriendsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is FriendsViewHolder){
            holder.bind(friend = mFriendsList[position])
        }
    }



    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var mCivProfileImg : CircleImageView = itemView.findViewById(R.id.civ_profile_image)
        private var mFriendUsername : TextView = itemView.findViewById(R.id.tv_friend_username)
        private var mFriendCity: TextView = itemView.findViewById(R.id.tv_friend_city)
        private var mImgFriendOnline : View = itemView.findViewById(R.id.img_online_friend)

        @SuppressLint("SetTextI18n")
        fun bind(friend: VKUser){
            itemView.apply {
                friend.avatar?.let { url->
                    Picasso.get().load(url).into(mCivProfileImg)
                }

                mFriendUsername.text = "${friend.name} ${friend.surName}"
                mFriendCity.text = itemView.context.getText(R.string.friends_no_city)
                friend.city?.let { mFriendCity.text = it }
                if(friend.isOnline == 1){
                    mImgFriendOnline.visibility = View.VISIBLE
                }else{
                    mImgFriendOnline.visibility = View.GONE
                }
            }

        }
    }
}