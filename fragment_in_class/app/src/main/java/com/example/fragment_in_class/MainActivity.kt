package com.example.fragment_in_class

import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragment_in_class.dummy.DummyContent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  jimmy.OnFragmentInteractionListener, jammy.OnListFragmentInteractionListener, AppCompatActivity() {


    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        var thejim: jimmy = supportFragmentManager.fragments.get(0) as jimmy
        thejim.setText(item.toString());

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

    override fun onFragmentInteraction(uri: Uri){
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
    }
}
