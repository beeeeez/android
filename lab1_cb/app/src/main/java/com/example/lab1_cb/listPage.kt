package com.example.lab1_cb

import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_list_page.*
import kotlinx.android.synthetic.main.content_list_page.*

class listPage : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_page)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        var stuff = intent.extras ?: return

        var booyah = stuff.getParcelable<Order>("theGoods")
        if(booyah!=null){
            Order.allOrders.add(booyah)
        }
        val temp = ArrayList<String>()



        var adapter= ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, Order.allOrders)

        listBoy.adapter=adapter

        sendBtn.setOnClickListener{
            view->
            finish()


        }


    }

    override fun finish(){
        var thestuff= Intent()

        var returnMe=Order.allOrders.size.toString()
        thestuff.putExtra("returnStuff",returnMe)
        setResult(RESULT_OK, thestuff)
        super.finish()
    }

}
