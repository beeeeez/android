package com.example.inclassweek6

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.example.inclassweek6.AppDB
import com.example.inclassweek6.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listview = lsResults


       // setSupportActionBar(toolbar)

       // fab.setOnClickListener { view ->
      //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
     //           .setAction("Action", null).show()
     //   }


    btnAdd.setOnClickListener { view ->
        val db = AppDB.getAppDataBase(getApplicationContext())
        val jimmy = Account(txtID.text.toString().toInt(), txtName.text.toString(), txtType.text.toString() )
        Log.d(jimmy.name, jimmy.id.toString())
        db!!.BankAccountDao().addAccount(jimmy)

    }

        btnUpdate.setOnClickListener { view ->
            val db = AppDB.getAppDataBase(getApplicationContext())
            val jimmy2 = Account(txtID.text.toString().toInt(), txtName.text.toString(), txtType.text.toString())
            val dub = db!!.BankAccountDao().updateaccount(jimmy2)
            Toast.makeText(baseContext, "updated id : "+ dub.toString(), Toast.LENGTH_SHORT).show()
        }

        btnGetCountOfAccounts.setOnClickListener { view ->
            val db = AppDB.getAppDataBase(getApplicationContext())
            val jimmy3 = db!!.BankAccountDao().allAccounts.size
            Toast.makeText(baseContext, jimmy3.toString(), Toast.LENGTH_SHORT).show()


        }

        btnGetAllAccounts.setOnClickListener{view ->
            val db = AppDB.getAppDataBase(getApplicationContext())
            val jimmy = db!!.BankAccountDao().allAccounts
            val doo = ArrayList<String>(jimmy.size)
            for(nm in jimmy){
                val thing = nm.id
                val thing2 = nm.name
                val thing3 = nm.type
                doo.add(thing.toString() + " - " + thing2 + " - "+thing3)
            }
            val listadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, doo )
            listview.adapter = listadapter

        }

        btnGetAccountsByName.setOnClickListener { view->
            val accountList = java.util.ArrayList<Account>()
            val db = AppDB.getAppDataBase(getApplicationContext())
            val jimmy = db!!.BankAccountDao().getAccountsByName(txtName.text.toString())
            val doo = ArrayList<String>()
            for(nm in jimmy){
                val thing = nm.id
                val thing2 = nm.name
                val thing3 = nm.type
                doo.add(thing.toString() + " - " + thing2 + " - "+thing3)
            }
            val listadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, doo )
            listview.adapter = listadapter

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
}
