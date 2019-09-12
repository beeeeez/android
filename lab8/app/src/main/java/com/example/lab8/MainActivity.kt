package com.example.lab8

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.lab8.topFrag

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_top.*
import kotlinx.android.synthetic.main.fragment_top.view.*

class MainActivity: OrderFragment.OnListFragmentInteractionListener, topFrag.OnFragmentInteractionListener, AppCompatActivity() {


     override fun onFragmentInteraction(order: Order){

         orderList.add(order)
         Toast.makeText(this, orderList.size.toString(), Toast.LENGTH_SHORT).show()


     }
    override fun onListFragmentInteraction(order: Order){
        var sendView = supportFragmentManager.findFragmentById(R.id.fragment) as topFrag
        sendView.setValues(order);

        /*fbox.setText(order.fname)
        lbox.setText(order.lname)

        when(order.type){
            "Milk Chocolate" ->{typebox.setSelection(0)} //he wanted get position --
            "Dark Chocolate" ->{typebox.setSelection(1)}
            "White Chocolate" ->{typebox.setSelection(2)}
            else -> {typebox.setSelection(0)}
        }
        if(order.shipping==true){
            eRadio.isChecked=true
            nRadio.isChecked=false
        }
        else{
            nRadio.isChecked=true
            eRadio.isChecked=false
        }*/


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val orderList =  ArrayList<Order>()
    }

}
