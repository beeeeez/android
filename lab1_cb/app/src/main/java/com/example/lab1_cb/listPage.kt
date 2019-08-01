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
            allOrders.add(booyah)
        }
        val temp = arrayListOf<String>();


        for(i in 0..allOrders.size-1){
            temp.add(allOrders[i].fname.toString() + " - "+ allOrders[i].lname.toString() + " - " + allOrders[i].number.toString())
        }




        var adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp)

        listBoy.adapter=adapter

        sendBtn.setOnClickListener{
            view->
            finish()


        }


    }

    override fun finish(){
        var thestuff= Intent()

        var returnMe=allOrders.size.toString()
        thestuff.putExtra("returnStuff",returnMe)
        setResult(RESULT_OK, thestuff)
        super.finish()
    }

    companion object {
        internal val allOrders = ArrayList<Order>()
    }

}
