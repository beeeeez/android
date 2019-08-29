package lab.examples.neit.jsongetapplication

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

import java.net.HttpURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.InputStream
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // WebServer Request URL
        val GetServerData = findViewById<View>(R.id.btnGetServerData) as Button

        GetServerData.setOnClickListener {
            // WebServer Request URL
            val serverURL = "http://www.geoplugin.net/json.gp?ip=64.17.241.5"

            // Use AsyncTask execute Method To Prevent ANR Problem
            LongOperation().execute(serverURL)
        }
    }

    fun readJSONFeed(URL: String): String {
        val stringBuilder = StringBuilder()
        val c: HttpURLConnection
        //HttpClient httpClient = new DefaultHttpClient();
        //HttpGet httpGet = new HttpGet(URL);
        try {
            //   HttpResponse response = httpClient.execute(httpGet);
            //   StatusLine statusLine = response.getStatusLine();
            val u = URL(URL)
            c = u.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.setRequestProperty("Content-length", "0")
            c.useCaches = false
            c.allowUserInteraction = false
            c.connectTimeout = TIMEOUT
            c.readTimeout = TIMEOUT
            c.connect()
            val statusCode = c.responseCode
            //int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                //                HttpEntity entity = response.getEntity();
                //                InputStream inputStream = entity.getContent();
                val reader = BufferedReader(InputStreamReader(c.inputStream))
                //                BufferedReader reader = new BufferedReader(
                //                        new InputStreamReader(inputStream));
                var line: String = ""
                while ({ line = reader.readLine(); line }() != null) {
                    stringBuilder.append(line)
                }
                reader.close()
            } else {
                Log.d("JSON", "Failed to download file")
            }
        } catch (e: Exception) {
            Log.d("readJSONFeed", e.localizedMessage)
        }

        return stringBuilder.toString()
    }

    private inner class LongOperation : AsyncTask<String, Void, String>() {

        //   TextView jsonParsed = (TextView)findViewById(R.id.jsonParsed);

        internal var City = findViewById<View>(R.id.txtCity) as TextView
        internal var Latitude = findViewById<View>(R.id.txtLatitude) as TextView
        internal var Longitude = findViewById<View>(R.id.txtLongitude) as TextView

        override fun doInBackground(vararg params: String): String {
            /************ Make Post Call To Web Server  */
            return readJSONFeed(params[0])
        }


        override fun onPostExecute(result: String) {
            // NOTE: You can call UI Element here.


            /****************** Start Parse Response JSON Data  */

            val OutputData = ""
            val jsonResponse: JSONObject

            try {

                /****** Creates a new JSONObject with name/value mappings from the JSON string.  */
                jsonResponse = JSONObject(result)

                /***** Returns the value mapped by name if it exists and is a JSONArray.  */
                /*******  Returns null otherwise.   */
                City.text = jsonResponse.getString("geoplugin_city")
                Latitude.text = jsonResponse.getString("geoplugin_latitude")
                Longitude.text = jsonResponse.getString("geoplugin_longitude")


            } catch (e: JSONException) {

                e.printStackTrace()
            }


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

    companion object {
        val TIMEOUT = 5000
    }
}
