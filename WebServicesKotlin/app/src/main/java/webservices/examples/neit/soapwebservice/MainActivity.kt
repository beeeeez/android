package webservices.examples.neit.soapwebservice

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.xmlpull.v1.XmlPullParserException

import java.io.IOException


class MainActivity : AppCompatActivity() {


    private val GetWeatherClickListener = View.OnClickListener {
        // Use AsyncTask execute Method To Prevent ANR Problem
        val requestedCity: String
        val requestedCountry: String
        requestedCity = txtCity.text.toString()
        requestedCountry = txtCountry.text.toString()
        WebOperation().execute(requestedCity, requestedCountry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GetWeather.setOnClickListener(GetWeatherClickListener)
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

                val requestedCity: String
                val requestedCountry: String

                requestedCity = params[0]
                requestedCountry = params[1]
                // This won't work because you need to pull components from the main thread
                //resultOfCall = GetWeatherInfo(City.getText().toString(), Country.getText().toString());
                resultOfCall = GetWeatherInfo(requestedCity, requestedCountry)
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
    private fun GetWeatherInfo(City: String, Country: String): String {


        val request = SoapObject(NAMESPACE, METHOD_NAME)


        val CityProp = PropertyInfo()
        CityProp.type = PropertyInfo.STRING_CLASS
        CityProp.name = "CityName"
        CityProp.setNamespace(NAMESPACE)
        CityProp.value = City
        request.addProperty(CityProp)

        val CountryProp = PropertyInfo()
        CountryProp.type = PropertyInfo.STRING_CLASS
        CountryProp.name = "CountryName"
        CountryProp.setNamespace(NAMESPACE)
        CountryProp.value = Country
        request.addProperty(CountryProp)

        // this works too
        //		request.addProperty("CityName",City);
        //		request.addProperty("CountryName", Country);
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.dotNet = true
        envelope.setOutputSoapObject(request)

        val ht: HttpTransportSE
        println(envelope.bodyOut.toString())
        ht = HttpTransportSE(URL)

        ht.debug = true
        ht.call(SOAP_ACTION, envelope)

        //SoapObject response = (SoapObject)envelope.getResponse();
        val response = envelope.response as SoapPrimitive

        return response.toString()
    }

    companion object {

        private val SOAP_ACTION = "http://www.webserviceX.NET/GetWeather"
        private val METHOD_NAME = "GetWeather"
        private val NAMESPACE = "http://www.webserviceX.NET"
        private val URL = "http://www.webservicex.net/globalweather.asmx"
    }
}
