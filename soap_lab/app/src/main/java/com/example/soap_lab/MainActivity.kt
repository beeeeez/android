package com.example.soap_lab

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

class MainActivity : AppCompatActivity() {



    private val getDefListen = View.OnClickListener {
        // Use AsyncTask execute Method To Prevent ANR Problem
        val word:String
        word=editText.text.toString()
        WebOperation().execute(word)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btn.setOnClickListener(getDefListen)
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


    private inner class WebOperation : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            var resultOfCall = "test"
            try {
                val word: String
                word=params[0]
                // This won't work because you need to pull components from the main thread
                //resultOfCall = GetWeatherInfo(City.getText().toString(), Country.getText().toString());
                resultOfCall = getDef(word)
            } catch (e: IOException) {
                Log.e("Allied Error", "Foo didn't work: " + e.message)
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e))
            } catch (e: XmlPullParserException) {
                Log.e("Allied Error", "Foo didn't work: " + e.message)
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e))
            }

            //    result.setText(resultOfCall);

            return resultOfCall
        }

        override fun onPostExecute(ResultOfCall: String) {

            result.text = ResultOfCall
        }
    }
        @Throws(IOException::class, XmlPullParserException::class)
        private fun getDef(word: String): String {

        val request = SoapObject(NAMESPACE, METHOD_NAME)
        val prop = PropertyInfo()
        prop.type = PropertyInfo.STRING_CLASS
        prop.name = "word"
        prop.setNamespace(NAMESPACE)
        prop.value = word
        request.addProperty(prop)
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)
        val ht: HttpTransportSE
        println(envelope.bodyOut.toString())
        ht = HttpTransportSE(URL)

        ht.debug = true
        ht.call(SOAP_ACTION, envelope)

        //SoapObject response = (SoapObject)envelope.getResponse();
            val response = envelope.response as SoapObject
            //val SomeVariable = response.getProperty(1) as SoapPrimitive
          //  val Definitions = response.getProperty("Definitions") as SoapObject
            val defs = response.getProperty("Definitions") as SoapObject
            val numDef =defs.propertyCount
            var stringo: String=""
            for (counter in 0 until numDef) {
                val def = defs.getProperty(counter) as SoapObject
                stringo+=def.getProperty("WordDefinition")

            }


        return stringo
    }


    companion object {

        private val SOAP_ACTION = "http://services.aonaware.com/webservices/Define"
        private val METHOD_NAME = "Define"
        private val NAMESPACE = "http://services.aonaware.com/webservices/"
        private val URL = "http://services.aonaware.com/DictService/DictService.asmx"
    }
}
