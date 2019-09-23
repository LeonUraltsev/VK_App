package ru.leonuraltsev.vk_sdk_app.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject
data class VKUser(
    val id:Int = 0,
    val name : String,
    val surName : String,
    val city : String? = null,
    val avatar : String? = null,
    val deactivated: Boolean = false,
    val isOnline : Int) : Parcelable{
    constructor(parcel : Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeString(surName)
        dest?.writeString(city)
        dest?.writeString(avatar)
        dest?.writeByte(if (deactivated) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKUser> {
        override fun createFromParcel(parcel: Parcel): VKUser {
            return VKUser(parcel)
        }

        override fun newArray(size: Int): Array<VKUser?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject) : VKUser {

            val id = json.optInt("id", 0)
            val name = json.optString("first_name", "")
            val surName = json.optString("last_name", "")
            val city = json.optJSONObject("city")?.let { it.optString("title") }
            val avatar = json.optString("photo_100", "")
            val deactivated = json.optBoolean("deactivated", false)
            val isOnline = json.optInt("online", 0)

            return VKUser(id,name,surName,city,avatar,deactivated,isOnline)

        }




    }
}