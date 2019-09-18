package com.example.androidfinal

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import kotlinx.android.synthetic.main.fragment_match_list.*
import kotlinx.android.synthetic.main.fragment_match_list.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [matchList.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [matchList.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class matchList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var teamM: teamModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_match_list, container, false)
        val serverURL = "http://172.26.132.241:8081/getteams"
        teamM =ViewModelProviders.of(this).get(teamModel::class.java)
        var text=view.textView;
     //   text.text="fluke a duk

       // text.text="fluke a muke"

        val timer = Timer()
        var teamList: String=""
        val t = object : TimerTask() {
           override fun run() {


               LongOperation().execute(serverURL)
              // var db = AppDB.getAppDataBase(getActivity()!!.getApplicationContext());
               //
            }
        }



        timer.scheduleAtFixedRate(t, 3000, 3000)
        var db = AppDB.getAppDataBase(getActivity()!!.getApplicationContext());
        var obv = androidx.lifecycle.Observer<List<Team>>(){
            @Override
            fun onChanged(@Nullable teams: List<Team>){
                var hammy = ""
                Log.d("hey ", "jimbo")
                for(nm in teams){
                    val thing = nm.name
                    val thing2 = nm.city
                    val thing3 = nm.record
                  //  hammy += thing + " - " + thing2 + " - "+thing3+"\n";
                 //   teamList+=hammy;
                }
              //  text.text = hammy
            }
        }
        LongOperation().execute(serverURL)
            var hammy = ""
            var teams = db!!.TeamDAO().getTeams()
            for(nm in teams){
                val thing = nm.name
                val thing2 = nm.city
                val thing3 = nm.record
                hammy += thing + " - " + thing2 + " - "+thing3+"\n";
            }
            text.text = hammy


        db!!.TeamDAO().watchTeams().observe(this, obv)
        var moveFrags = view.moveFragments
        moveFrags.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.act))

        return view
    }



    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    /***************************************************************************************************
     * JSON Stuff
     *************************************************************************************************/


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
                //div.text = jsonResponse.getString("Division")
                val arr = jsonResponse.getJSONArray("teams")
                var bing: String=""

                val list = ArrayList<String>(arr.length())
                val db = AppDB.getAppDataBase(getActivity()!!.getApplicationContext());
                if(db!!.TeamDAO().getTeams().isEmpty()){
                    for (jsonIndex in 0..(arr.length() - 1)) {
                        // bing+="team: "+arr.getJSONObject(jsonIndex).getString("name")+" \n"

                        // bing+= "name " +arr.getJSONObject(jsonIndex).getString("name") + " wgt " +arr.getJSONObject(jsonIndex).getString("weight") + " pos " +arr.getJSONObject(jsonIndex).getString("position")+"\n"
                        // Log.d("JSON", arr.getJSONObject(jsonIndex).getString("title"))
                        //list.add("name " +arr.getJSONObject(jsonIndex).getString("name") + " wgt " +arr.getJSONObject(jsonIndex).getString("weight") + " pos " +arr.getJSONObject(jsonIndex).getString("position"));


                        var jimmy = Team(arr.getJSONObject(jsonIndex).getString("name"),arr.getJSONObject(jsonIndex).getString("city"), arr.getJSONObject(jsonIndex).getString("record") );
                        db!!.TeamDAO().addTeam(jimmy);
                    }
                }
                else
                {
                    var list = db!!.TeamDAO().getTeams()
                    var count: Int =0
                    for (jsonIndex in 0..(arr.length() - 1)) {
                        var jimmy = list.get(count);
                        jimmy.name =arr.getJSONObject(jsonIndex).getString("name")
                        jimmy.city =arr.getJSONObject(jsonIndex).getString("city")
                        jimmy.record =arr.getJSONObject(jsonIndex).getString("record")

                        count++
                        Log.d("updating", jimmy.name);
                        db!!.TeamDAO().updateTeam(jimmy);


                    }
                }
               // Log.d("fuck me ", bing);

              //  val listadapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list )
             //   listy.adapter = listadapter
             //   info.text=bing;


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

    /***************************************************************************************************
     * End of JSON Stuff
     *************************************************************************************************/





















    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {



            val TIMEOUT = 5000

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment matchList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            matchList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
