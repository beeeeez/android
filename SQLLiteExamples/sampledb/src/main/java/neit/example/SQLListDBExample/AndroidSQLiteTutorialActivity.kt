package neit.example.SQLListDBExample

import android.app.Activity
import android.os.Bundle
import android.util.Log


import neit.example.ListViewExample.R
import neit.example.SQLListDBExample.DatabaseHandler


class AndroidSQLiteTutorialActivity : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_db)

        val db = DatabaseHandler(this)

        /**
         * CRUD Operations
         */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..")
        db.addContact(Contact("Ravi", "9100000000"))
        db.addContact(Contact("Srinivas", "9199999999"))
        db.addContact(Contact("Tommy", "9522222222"))
        db.addContact(Contact("Karthik", "9533333333"))

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..")
        val contacts = db.allContacts

        for (cn in contacts) {
            val log = "Id: " + cn.id + " ,Name: " + cn.name + " ,Phone: " + cn.phoneNumber
            // Writing Contacts to log
            Log.d("Name: ", log)

        }
    }
}