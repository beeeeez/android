package com.example.midterm_cb

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_results.*

class MainActivity : AppCompatActivity() {
    private var request_code = 5

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
            //R.id.action_settings -> true
            R.id.submit -> {
                var i = Intent(this, ResultsActivity::class.java)
                i.putExtra("amount", numBox.text.toString().toInt())
                var type=0
                if(butGroup.checkedRadioButtonId==withBut.id){
                    type=1
                }
                else if(butGroup.checkedRadioButtonId==depBut.id){
                    type=2
                }
                else{
                    type=3
                }
                i.putExtra("type", type)
                startActivityForResult(i, request_code);
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val stuff  = data?.extras?.getInt("returnStuff")
        Toast.makeText(this, stuff.toString(), Toast.LENGTH_LONG).show()
        balanceText.text=stuff.toString()


    }
}
