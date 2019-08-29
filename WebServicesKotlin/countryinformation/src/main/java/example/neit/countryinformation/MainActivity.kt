package example.neit.countryinformation

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import example.neit.mortagepayment.R


import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.xmlpull.v1.XmlPullParserException

import java.io.IOException


import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)



        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
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
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private inner class WebOperation : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            var resultOfCall = "test"
            try {

                val CountryISO: String
                CountryISO = params[0]

                resultOfCall = GetCountryInformation(CountryISO)
            } catch (e: IOException) {
                Log.e("Allied Error", "Foo didn't work: " + e.message)
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e))
            } catch (e: XmlPullParserException) {
                Log.e("Allied Error", "Foo didn't work: " + e.message)
                Log.e("Allied Error2", "Full stack track:" + Log.getStackTraceString(e))
            }


            return resultOfCall
        }

        override fun onPostExecute(ResultOfCall: String) {
            response.text = ResultOfCall
        }
    }

    fun GetCountryInfo(view: View) {
        // Use AsyncTask execute Method To Prevent ANR Problem
        val requestedCountryISO: String


        requestedCountryISO = txtCountryISO.text.toString()

        WebOperation().execute(requestedCountryISO)

    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun GetCountryInformation(CountryISO: String): String {


        val request = SoapObject(NAMESPACE, METHOD_NAME)

        val YearsProp = PropertyInfo()
        YearsProp.type = PropertyInfo.STRING_CLASS
        YearsProp.name = "sCountryISOCode"
        YearsProp.setNamespace(NAMESPACE)
        YearsProp.value = CountryISO
        request.addProperty(YearsProp)

        // this works too
        //		request.addProperty("sCountryISOCode",CountryISO);

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)

        val ht: HttpTransportSE
        println(envelope.bodyOut.toString())
        ht = HttpTransportSE(URL)

        ht.debug = true
        ht.call(SOAP_ACTION, envelope)


        val CountryName: String
        val CapitalCity: String
        val CountryFlag: String
        val str: String
        var results = ""

        val response = envelope.response as SoapObject
        CountryName = response.getPrimitivePropertyAsString("sName")
        CapitalCity = response.getProperty(2).toString()
        CountryFlag = response.getPrimitivePropertyAsString("sCountryFlag")


        val Languages = response.getProperty("Languages") as SoapObject
        val NumberOfLanguages = Languages.propertyCount
        for (counter in 0 until NumberOfLanguages) {
            val language = Languages.getProperty(counter) as SoapObject
            val LanguageName = language.getProperty("sName") as SoapPrimitive

            results = "$results$LanguageName\n\r-----\n\r"
        }

        str = "Country = " + CountryName + "\n\r" +
                "Capital = " + CapitalCity + "\n\r" +
                "Country Flag Image = " + CountryFlag + "\n\r" +
                "Languages = " + results

        return str
    }

    companion object {

        private val SOAP_ACTION = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/FullCountryInfo"
        private val METHOD_NAME = "FullCountryInfo"
        private val NAMESPACE = "http://www.oorsprong.org/websamples.countryinfo"
        private val URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso"
    }
}
