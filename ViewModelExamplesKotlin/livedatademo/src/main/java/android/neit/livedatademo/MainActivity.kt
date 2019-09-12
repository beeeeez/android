package android.neit.livedatademo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var result: TextView? = null
    private var number1: EditText? = null
    private var number2: EditText? = null
    private var statusText: TextView? = null

    //private var viewModel: DemoViewModel? = null
    private lateinit var viewModel: DemoViewModel
   // private var resultObserver: Observer<String>? = null
    // Create an anonymous implementation of OnClickListener
    private val ResultClickListener = View.OnClickListener {
        try {
            val firstNumber = number1!!.text.toString()
            val secondNumber = number2!!.text.toString()

            val resultText: Float

            resultText = java.lang.Float.valueOf(firstNumber).toFloat() + java.lang.Float.valueOf(secondNumber).toFloat()
            result!!.text = java.lang.Float.toString(resultText)
            //viewModel.setResult(result.getText().toString());
            //viewModel!!.result.value = result!!.text.toString()
            //viewModel.result.value= result!!.text.toString()

            viewModel.result.value =(result?.getText().toString());

        } catch (e: NumberFormatException) {
            result!!.text = "Error in calculation are both inputs numbers?"
            Log.e("OnClickListener", "Invalid Numbers - " + number1!!.text.toString() +
                    " - " + number2!!.text.toString())


        }
    }
    // Create the observer which updates the UI.
    //private  Observer<String> nameObserver;

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resultValue: String

        result = findViewById<View>(R.id.result) as TextView
        number1 = findViewById<View>(R.id.Number1) as EditText
        number2 = findViewById<View>(R.id.Number2) as EditText
        statusText = findViewById<View>(R.id.statusText) as TextView


        viewModel = ViewModelProviders.of(this).get(DemoViewModel::class.java)

//        resultObserver = Observer { newResult ->
//            // Update the UI
//            result!!.text = newResult
//        }

        viewModel.result.observe(this, Observer { data ->
            // Set the text exposed by the LiveData
            //view?.findViewById<TextView>(R.id.text)?.text = data
            findViewById<TextView>(R.id.result)?.text = data
        })



//        //viewModel.result.observe(this, resultObserver!!)
//        viewModel.result.observe(this, Observer { data ->
//            // Set the text exposed by the LiveData
//            result?.text = data
//        })
      //  displayResult(viewModel.getResult());

        //        if (savedInstanceState == null)
        //        {
        //        	result.setText("0");
        //        	statusText.setText("Welcome To The Program!");
        //        }
        //        else
        //        {
        //  //      	result.setText(savedInstanceState.getString("RESULT_VALUE"));
        //        	statusText.setText("Welcome Back!");
        //        }


        val addButton = findViewById<View>(R.id.addOperation) as Button
        addButton.setOnClickListener(ResultClickListener)

    }


    private fun displayResult(resultString: String) {
        result!!.text = resultString
    }

    // use the home button to get onStop to happen, don't cancel the emulator.
    override fun onStop() {
        super.onStop()

    }

    companion object {

        val colorPrefsFile = "myColorPrefsFile"
    }


}
