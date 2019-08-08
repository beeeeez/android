package com.example.midterm_cb

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.content_results.*

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        var stuff = intent.extras

        var type = stuff?.getInt("type")
        var holder = ""
        if(type==1){
            changeMe.text= "Withdrawal : "
            amount.text=stuff!!.getInt("amount").toString()
            val temp = stuff!!.getInt("amount")
            withStat(temp)
        }
        else if(type==2){
            changeMe.text= "Deposit : "
            amount.text=stuff!!.getInt("amount").toString()
            val temp = stuff!!.getInt("amount")
            depStat(temp)
        }
        else if(type==3) {
            changeMe.text= "Inquiry :  --- "
            amount.text=""
        }

        totalBalance.text=statBal.toString()

        closeBtn.setOnClickListener{ view ->
            finish()
        }









    }

    override fun finish(){
        var thestuff= Intent()

        var returnMe=statBal
        thestuff.putExtra("returnStuff",returnMe)
        setResult(RESULT_OK, thestuff)
        super.finish()
    }

    companion object {
        internal var statBal: Int=0
                fun depStat(jimmy: Int){
                    statBal+=jimmy
                }
        fun withStat(jimmy: Int){
            statBal-=jimmy
        }

    }




}
