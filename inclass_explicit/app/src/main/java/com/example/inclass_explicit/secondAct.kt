package com.example.inclass_explicit

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.content_second.*

class secondAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val stuff = intent.extras ?: return

        val stringywingy = stuff.getString("theGoods");
        dumpbaby.text=stringywingy;

        flub.setOnClickListener { view->

            finish()
        }


    }
    override fun finish(){
        val thestuff= Intent()
        val returnMe=editText.text.toString()
        thestuff.putExtra("returnStuff",returnMe)
        setResult(RESULT_OK, thestuff)
        super.finish()
    }


}
