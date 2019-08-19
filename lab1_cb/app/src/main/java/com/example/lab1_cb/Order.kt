package com.example.lab1_cb

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(val i: Int,val f: String?, val l: String?, val t: String?, val n: Int, val s: Boolean, val p: Float) : Parcelable {

    public val id = i

    public val fname = f

    public val lname = l

    public val type = t

    public val number = n

    public val shipping = s

    public val price = p



}

