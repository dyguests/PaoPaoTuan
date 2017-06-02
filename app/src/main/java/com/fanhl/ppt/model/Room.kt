package com.fanhl.ppt.model

import android.os.Parcel
import android.os.Parcelable

data class Room(
        val id: Long,
        val gameId: Long? = null
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Room> = object : Parcelable.Creator<Room> {
            override fun createFromParcel(source: Parcel): Room = Room(source)
            override fun newArray(size: Int): Array<Room?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readValue(Long::class.java.classLoader) as Long?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeValue(gameId)
    }
}