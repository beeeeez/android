package com.example.lab1_cb

import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


import kotlinx.android.synthetic.main.activity_list_page.*
import kotlinx.android.synthetic.main.content_list_page.*
import neit.example.SQLListDBExample.DatabaseHandler

class listPage : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_page)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val db = DatabaseHandler(this)
        var stuff = intent.extras ?: return
        var booyah = stuff!!.getParcelable<Order>("theGoods")
        var booyah2 = stuff.getString("theGood")
        if(booyah!=null){


            db.addOrder(booyah!!)
            var ordersList=db.allOrders
            var formatList= ArrayList<String>()
            for(order in ordersList){
                val o1 = order.fname
                val o2= order.lname
                val o3=order.id
                val o4=order.price
                formatList.add(o1 +" " +o2 + " - ID : "+o3 + " - total : "+o4)

            }



            var adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formatList)

            listBoy.adapter=adapter

        }
        else if(booyah2!=null){
            var jimmy = db.getOrder(booyah2.toInt())
            var formatList= ArrayList<String>()
            formatList.add(jimmy.fname + " " + jimmy.lname + " -ID: "+jimmy.id + " total: "+jimmy.price);
            var adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formatList)

            listBoy.adapter=adapter
            }











        sendBtn.setOnClickListener{
            view->
            finish()


        }

        priceBtn.setOnClickListener { view->


            var ordersList= db.getOrderbyPrice(editText.text.toString().toFloat())

            var formatList= ArrayList<String>()
            for(order in ordersList){
                val o1 = order.fname
                val o2= order.lname
                val o3=order.id
                val o4=order.price
                formatList.add(o1 +" " +o2 + " - ID : "+o3 + " - total : "+o4)

            }

            var adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, formatList)

            listGirl.adapter=adapter

        }


    }

    override fun finish(){
        var thestuff= Intent()
        var db = DatabaseHandler(this)
        var returnMe=db.OrdersCount.toString()
        thestuff.putExtra("returnStuff",returnMe)
        setResult(RESULT_OK, thestuff)
        super.finish()
    }
/*
    companion object {
        internal val allOrders = ArrayList<Order>()
    }
*/
}
