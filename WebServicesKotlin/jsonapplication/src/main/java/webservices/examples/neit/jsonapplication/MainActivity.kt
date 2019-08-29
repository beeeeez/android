package webservices.examples.neit.jsonapplication

import android.app.Dialog
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import java.net.HttpURLConnection
import java.io.InputStreamReader
import java.net.URL

//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.OutputStreamWriter
import java.io.UnsupportedEncodingException
import java.net.URLConnection
import java.net.URLEncoder

//http://androidexample.com/Restful_Webservice_Call_And_Get_And_Parse_JSON_Data-_Android_Example/index.php?view=article_discription&aid=101&aaid=123
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val GetServerData = findViewById<View>(R.id.GetServerData) as Button

        GetServerData.setOnClickListener {
            // WebServer Request URL
            val serverURL = "https://androidexample.com/media/webservice/JsonReturn.php"

            // Use AsyncTask execute Method To Prevent ANR Problem
            LongOperation().execute(serverURL)
        }
    }

    // Class with extends AsyncTask class

    private inner class LongOperation : AsyncTask<String, Void, Void>() {

        // Required initialization
        //HttpURLConnection c;
        //private final HttpClient Client = new DefaultHttpClient();
        private var Content: String? = null
        private var Error: String? = null
        private val Dialog = ProgressDialog(this@MainActivity)
        internal var data = ""
        internal var uiUpdate = findViewById<View>(R.id.output) as TextView
        internal var jsonParsed = findViewById<View>(R.id.jsonParsed) as TextView
        internal var sizeData = 0
        internal var serverText = findViewById<View>(R.id.serverText) as EditText


        override fun onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            Dialog.setMessage("Please wait..")
            Dialog.show()

            try {
                // Set Request parameter
                data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + serverText.text

            } catch (e: UnsupportedEncodingException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }

        // Call after onPreExecute method
        override fun doInBackground(vararg urls: String): Void? {

            /************ Make Post Call To Web Server  */
            var reader: BufferedReader? = null

            // Send data
            try {

                // Defined URL  where to send data
                val url = URL(urls[0])

                // Send POST data request

                val conn = url.openConnection()
                conn.doOutput = true
                val wr = OutputStreamWriter(conn.getOutputStream())
                wr.write(data)
                wr.flush()

                // Get the server response

                reader = BufferedReader(InputStreamReader(conn.getInputStream()))
                val sb = StringBuilder()
                var line: String? = null


                // Read Server Response
               // while ((line = reader.readLine()) != null) {
                while ({ line = reader.readLine(); line }() != null) {

               //for (line in reader.lines()){
                    // Append server response in string
                   sb.append(line!! + "")
                }

                // Append Server Response To Content String
                Content = sb.toString()
            } catch (ex: Exception) {
                Error = ex.message
            } finally {
                try {

                    reader!!.close()
                } catch (ex: Exception) {
                }

            }

            /** */
            return null
        }

        override fun onPostExecute(unused: Void?) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss()

            if (Error != null) {

                uiUpdate.text = "Output : " + Error!!

            } else {

                // Show Response Json On Screen (activity)
                uiUpdate.text = Content

                /****************** Start Parse Response JSON Data  */

                var OutputData = ""
                val jsonResponse: JSONObject

                try {

                    /****** Creates a new JSONObject with name/value mappings from the JSON string.  */
                    jsonResponse = JSONObject(Content)

                    /***** Returns the value mapped by name if it exists and is a JSONArray.  */
                    /*******  Returns null otherwise.   */
                    val jsonMainNode = jsonResponse.optJSONArray("Android")

                    /*********** Process each JSON Node  */

                    val lengthJsonArr = jsonMainNode.length()

                    for (i in 0 until lengthJsonArr) {
                        /****** Get Object for each JSON node. */
                        val jsonChildNode = jsonMainNode.getJSONObject(i)

                        /******* Fetch node values  */
                        val name = jsonChildNode.optString("name").toString()
                        val number = jsonChildNode.optString("number").toString()
                        val date_added = jsonChildNode.optString("date_added").toString()


                        OutputData += (" Name           : " + name + " "
                                + "Number      : " + number + " "
                                + "Time                : " + date_added + "  "
                                + "--------------------------------------------------  ")


                    }
                    /****************** End Parse Response JSON Data  */

                    //Show Parsed Output on screen (activity)
                    jsonParsed.text = OutputData


                } catch (e: JSONException) {

                    e.printStackTrace()
                }


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
}
