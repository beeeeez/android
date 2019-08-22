package neit.example.ListViewExample

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class SimpleListViewExample : ListActivity() {


    internal var countries = arrayOf(

            "Samsung", "Nokia", "Blackberry", "LG", "Motorola", "Apple", "Karbon", "Micromax", "Zen")

    /** Called when the activity is first created.  */

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var listview = listView

        // Declaring ArrayAdapter for the default listview
        val listadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)

        // Setting ArrayAdapter for the default listview

        listview.adapter = listadapter

        // Defining ItemClick event Listener

        //       OnItemClickListener listener = new OnItemClickListener() {

        // This method will be triggered when an item in the listview is clicked ( or touched )

        listview.onItemClickListener = OnItemClickListener { adapterView, view, i, l -> Toast.makeText(baseContext, "You selected : " + countries[i], Toast.LENGTH_SHORT).show() }

        // Setting an ItemClickEvent Listener for the listview
        // In this example we are making use the default listview
        //     getListView().setOnItemClickListener(listener);

    }

}

