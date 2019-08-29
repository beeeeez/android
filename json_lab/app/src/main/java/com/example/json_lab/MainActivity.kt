package com.example.json_lab

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btn.setOnClickListener {
            val serverURL = "http://3.83.3.182/jsonfootballservice/footballteams/GetFootballTeam"

            // Use AsyncTask execute Method To Prevent ANR Problem
            LongOperation().execute(serverURL)
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

    private inner class LongOperation : AsyncTask<String, Void, String>() {


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
                div.text = jsonResponse.getString("Division")
                val arr = jsonResponse.getJSONArray("roster")
                var bing: String=""

                val list = ArrayList<String>(arr.length())
                for (jsonIndex in 0..(arr.length() - 1)) {

                    bing+= "name " +arr.getJSONObject(jsonIndex).getString("name") + " wgt " +arr.getJSONObject(jsonIndex).getString("weight") + " pos " +arr.getJSONObject(jsonIndex).getString("position")+"\n"
                   // Log.d("JSON", arr.getJSONObject(jsonIndex).getString("title"))
                    list.add("name " +arr.getJSONObject(jsonIndex).getString("name") + " wgt " +arr.getJSONObject(jsonIndex).getString("weight") + " pos " +arr.getJSONObject(jsonIndex).getString("position"));

                }
                val listadapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list )
                listy.adapter = listadapter
                info.text=bing;


            } catch (e: JSONException) {

                e.printStackTrace()
            }


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
            val u = java.net.URL(URL)
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
            Log.d("readJSONFeed", e.localizedMessage!!)
        }

        return stringBuilder.toString()
    }

    companion object {
        val TIMEOUT = 5000
    }
}
