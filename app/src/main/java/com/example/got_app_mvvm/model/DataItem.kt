package com.example.got_app_mvvm.model

import android.os.Parcel
import android.os.Parcelable

data class DataItem(
    val family: String?,
    val firstName: String?,
    val fullName: String?,
    val id: Int,
    val image: String?,
    val imageUrl: String?,
    val lastName: String?,
    val title: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(family)
        parcel.writeString(firstName)
        parcel.writeString(fullName)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(imageUrl)
        parcel.writeString(lastName)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataItem> {
        override fun createFromParcel(parcel: Parcel): DataItem {
            return DataItem(parcel)
        }

        override fun newArray(size: Int): Array<DataItem?> {
            return arrayOfNulls(size)
        }
    }
}