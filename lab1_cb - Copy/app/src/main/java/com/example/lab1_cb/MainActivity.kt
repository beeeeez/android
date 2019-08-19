package com.example.lab1_cb

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*

class MainActivity : AppCompatActivity() {
    var CandyOrders = ArrayList<Order>()
    private var request_code = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        saveButton.setOnClickListener { view ->
            val timmy: Boolean
            if (shipbox.checkedRadioButtonId == nRadio.id) {
                timmy = false
            } else {
                timmy = true
            }

            val jimmy = Order(
                fbox.text.toString(),
                lbox.text.toString(),
                typebox.selectedItem.toString(),
                numbox.text.toString().toInt(),
                timmy
            )


                CandyOrders.add(jimmy)
           ans.text = "Order added, now there is " + CandyOrders.size + " orders"


        }

        resultsBtn.setOnClickListener {view->


            var timmy: Boolean
            if (shipbox.checkedRadioButtonId == nRadio.id) {
                timmy = false
            } else {
                timmy = true
            }

            var jimmy = Order(
                fbox.text.toString(),
                lbox.text.toString(),
                typebox.selectedItem.toString(),
                numbox.text.toString().toInt(),
                timmy
            )

            var i = Intent(this, listPage::class.java)
            i.putExtra("theGoods", jimmy)
            i.putExtra("theList", CandyOrders)
            startActivityForResult(i, request_code);

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
            R.id.new_item -> {fbox.setText(""); lbox.setText(""); numbox.setText(""); shipbox.clearCheck(); typebox.setSelection(0); return true}
            R.id.dblOrd -> {val tempy = numbox.text.toString().toInt()*2;numbox.setText(tempy.toString()); return true}
            R.id.firstOrd -> {
                fbox.setText(CandyOrders[0].fname)
                lbox.setText(CandyOrders[0].lname)
                when(CandyOrders[0].type){
                    "Milk Chocolate" ->{typebox.setSelection(0)} //he wanted get position -- figure that shit out some other time --
                    "Dark Chocolate" ->{typebox.setSelection(1)}
                    "White Chocolate" ->{typebox.setSelection(2)}
                    else -> {typebox.setSelection(0)}
                }
                numbox.setText(CandyOrders[0].number.toString())
                if(CandyOrders[0].shipping==true){
                    eRadio.isChecked=true
                    nRadio.isChecked=false
                }
                else{
                    nRadio.isChecked=true
                    eRadio.isChecked=false
                }

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val stuff  = data?.extras?.getString("returnStuff")
        Toast.makeText(this, stuff, Toast.LENGTH_LONG).show()
        ans.text="there are "+stuff+" orders in the list now";

        //https://www.techotopia.com/index.php/Android_Explicit_Intents_%E2%80%93_A_Kotlin_Example
    }
}




