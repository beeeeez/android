package android.neit.viewdatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.content_main.*

class Demo : FragmentActivity() {


    private var viewModel: DemoViewModel? = null
    // Create an anonymous implementation of OnClickListener
    private val ResultClickListener = OnClickListener {
        try {
            val firstNumber = Number1.text.toString()
            val secondNumber = Number2.text.toString()

            val resultText: Float

            resultText = java.lang.Float.valueOf(firstNumber).toFloat() + java.lang.Float.valueOf(secondNumber).toFloat()
            result.text = java.lang.Float.toString(resultText)
            viewModel!!.result = result.text.toString()
        } catch (e: NumberFormatException) {
            result!!.text = "Error in calculation are both inputs numbers?"
            Log.e("OnClickListener", "Invalid Numbers - " + Number1.text.toString() +
                    " - " + Number2.text.toString())


        }
    }

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resultValue: String


        viewModel = ViewModelProviders.of(this).get(DemoViewModel::class.java!!)
        displayResult(viewModel!!.result)

        //        if (savedInstanceState == null)
        //        {
        //        	result.setText("0");
        //        	statusText.setText("Welcome To The Program!");
        //        }
        //        else
        //        {
        //        	result.setText(savedInstanceState.getString("RESULT_VALUE"));
        //        	statusText.setText("Welcome Back!");
        //        }


        addOperation.setOnClickListener(ResultClickListener)

    }


    private fun displayResult(resultString: String?) {
        result.text = resultString
    }

    // use the home button to get onStop to happen, don't cancel the emulator.
    override fun onStop() {
        super.onStop()

    }

    // in Windows - press ctrl-F11 to change orientation
    override fun onSaveInstanceState(outState: Bundle) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState)
        outState.putString("RESULT_VALUE", result.text.toString())

    }

    companion object {

        val colorPrefsFile = "myColorPrefsFile"
    }

}

