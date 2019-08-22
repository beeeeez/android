package neit.example.SQLListDBExample

import java.util.ArrayList

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.inclass_week5.Account


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Getting All accounts
    // Select All Query
    // looping through all rows and adding to list
    // Adding account to list
    // return account list
    val allaccounts: List<Account>
        get() {
            val accountList = ArrayList<Account>()
            val selectQuery = "SELECT  * FROM $TABLE_accounts"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val account = Account()
                    account.id = Integer.parseInt(cursor.getString(0))
                    account.name = cursor.getString(1)
                    account.type = cursor.getString(2)
                    accountList.add(account)
                } while (cursor.moveToNext())
            }
            return accountList
        }


    // Getting accounts Count
    // return count
    val accountsCount: Int
        get() {
            val countQuery = "SELECT  * FROM $TABLE_accounts"
            val db = this.readableDatabase
            val cursor = db.rawQuery(countQuery, null)
        //   cursor.close()
            return cursor.count
        }

    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_accounts_TABLE = ("CREATE TABLE " + TABLE_accounts + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT" + ")")
        db.execSQL(CREATE_accounts_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_accounts")

        // Create tables again
        onCreate(db)
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new account
    internal fun addAccount(account: Account) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, account.name) // account Name
        values.put(KEY_TYPE, account.type) // account Phone
        Log.d("fartman:" , "he comes bro")
        // Inserting Row
        db.insert(TABLE_accounts, null, values)
        db.close() // Closing database connection
    }

    internal fun getAccountbyName(name: String): List<Account>{
        val db = this.readableDatabase

        val cursor = db.query(TABLE_accounts, arrayOf(KEY_ID, KEY_NAME, KEY_TYPE), "$KEY_NAME=?",
            arrayOf(name), null, null, null, null)
        cursor?.moveToFirst()
        val accountList = ArrayList<Account>()
        if (cursor.moveToFirst()) {
            do {
                val account = Account()
                account.id = Integer.parseInt(cursor.getString(0))
                account.name = cursor.getString(1)
                account.type = cursor.getString(2)
                accountList.add(account)
            } while (cursor.moveToNext())
        }
        return accountList
    }

    // Getting single account
    internal fun getAccount(id: Int): Account {
        val db = this.readableDatabase

        val cursor = db.query(TABLE_accounts, arrayOf(KEY_ID, KEY_NAME, KEY_TYPE), "$KEY_ID=?",
                arrayOf(id.toString()), null, null, null, null)
        cursor?.moveToFirst()

// return account
        return Account(Integer.parseInt(cursor!!.getString(0)),
                cursor.getString(1), cursor.getString(2))
    }

    // Updating single account
    fun updateaccount(account: Account): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, account.name)
        values.put(KEY_TYPE, account.type)

        // updating row
        db.update(TABLE_accounts, values, "$KEY_ID = ?",
                arrayOf(account.id.toString()))

        return account.id
    }

    // Deleting single account
    fun deleteaccount(account: Account) {
        val db = this.writableDatabase
        db.delete(TABLE_accounts, "$KEY_ID = ?",
                arrayOf(account.id.toString()))
        db.close()
    }

    companion object {

        // All Static variables
        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "accountsManager"

        // accounts table name
        private val TABLE_accounts = "accounts"

        // accounts Table Columns names
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_TYPE = "type"
    }

}
