package com.example.lab8

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class Order{
     var fname=""
     var lname=""
     var type=""
     var number=0
     var shipping=false

    fun Order(fname: String, lname : String, type : String, number: Int, shipping: Boolean){
        this.fname=fname
        this.type=type
        this.number=number
        this.lname=lname
        this.shipping=shipping


    }

}
