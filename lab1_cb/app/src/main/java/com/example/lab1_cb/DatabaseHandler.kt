package neit.example.SQLListDBExample

import java.util.ArrayList

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.lab1_cb.Order


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Getting All Orders
    // Select All Query
    // looping through all rows and adding to list
    // Adding Order to list
    // return Order list
    val allOrders: List<Order>
        get() {
            val OrderList = ArrayList<Order>()
            val selectQuery = "SELECT  * FROM $TABLE_Orders"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {

                    var id = Integer.parseInt(cursor.getString(0))
                    var fname = cursor.getString(1)
                    var lname = cursor.getString(2)
                    var type = cursor.getString(3)
                    var number = Integer.parseInt(cursor.getString(4))
                    var shipping=false
                    if (Integer.parseInt(cursor.getString(5))==1){
                        var shipping=true;
                    }
                    var price = Integer.parseInt(cursor.getString(6))
                    var price2 = price.toFloat()
                    val Order = Order(id, fname, lname, type, number, shipping, price2)
                        OrderList.add(Order)
                } while (cursor.moveToNext())
            }
            return OrderList
        }


    // Getting Orders Count
    // return count
    val OrdersCount: Int
        get() {
            val countQuery = "SELECT  * FROM $TABLE_Orders"
            val db = this.readableDatabase
            val cursor = db.rawQuery(countQuery, null)
        //   cursor.close()
            return cursor.count
        }

    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_Orders_TABLE = ("CREATE TABLE " + TABLE_Orders + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT,"  + KEY_LNAME + " TEXT,"
                + KEY_TYPE + " TEXT," +   KEY_NUMBER + " INTEGER," + KEY_SHIPPING + "INTEGER," + KEY_PRICE + "REAL )");
        db.execSQL(CREATE_Orders_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_Orders")

        // Create tables again
        onCreate(db)
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Order
    internal fun addOrder(Order: Order) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_FNAME, Order.fname) // Order Name
        values.put(KEY_LNAME, Order.lname) // Order Phone
        values.put(KEY_TYPE, Order.type) // Order Name
        values.put(KEY_NUMBER, Order.number) // Order Phone
        values.put(KEY_SHIPPING, Order.shipping) // Order Name

        Log.d("fartman:" , "he comes bro")
        // Inserting Row
        db.insert(TABLE_Orders, null, values)
        db.close() // Closing database connection
    }

    /*
    internal fun getOrderbyName(name: String): List<Order>{
        val db = this.readableDatabase

        val cursor = db.query(TABLE_Orders, arrayOf(KEY_ID, KEY_NAME, KEY_TYPE), "$KEY_NAME=?",
            arrayOf(name), null, null, null, null)
        cursor?.moveToFirst()
        val OrderList = ArrayList<Order>()
        if (cursor.moveToFirst()) {
            do {
                val Order = Order()
                Order.id = Integer.parseInt(cursor.getString(0))
                Order.name = cursor.getString(1)
                Order.type = cursor.getString(2)
                OrderList.add(Order)
            } while (cursor.moveToNext())
        }
        return OrderList
    }
*/
    // Getting single Order
    internal fun getOrder(id: Int): Order {
        val db = this.readableDatabase

        val cursor = db.query(
            TABLE_Orders,
            arrayOf(KEY_ID, KEY_FNAME, KEY_LNAME, KEY_TYPE, KEY_NUMBER, KEY_SHIPPING, KEY_PRICE),
            "$KEY_ID=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()

// return Order fucdnsgf,mdf

        var id = Integer.parseInt(cursor!!.getString(0))
        var fname = cursor!!.getString(1)
        var lname = cursor!!.getString(2)
        var type = cursor!!.getString(3)
        var number = Integer.parseInt(cursor!!.getString(4))
        var shipping = false
        if (Integer.parseInt(cursor!!.getString(5)) == 1) {
            shipping = true
        }
        var price = Integer.parseInt(cursor.getString(6))
        var price2 = price.toFloat()
        return Order(id, fname, lname, type, number, shipping, price2)

    }
/*
    // Updating single Order
    fun updateOrder(Order: Order): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, Order.name)
        values.put(KEY_TYPE, Order.type)

        // updating row
        db.update(TABLE_Orders, values, "$KEY_ID = ?",
                arrayOf(Order.id.toString()))

        return Order.id
    }
*/
    // Deleting single Order
    fun deleteOrder(Order: Order) {
        val db = this.writableDatabase
        db.delete(TABLE_Orders, "$KEY_ID = ?",
                arrayOf(Order.id.toString()))
        db.close()
    }

    companion object {

        // All Static variables
        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "OrdersManager"

        // Orders table name
        private val TABLE_Orders = "Orders"

        // Orders Table Columns names
        private val KEY_ID = "id"
        private val KEY_FNAME = "fname"
        private val KEY_LNAME = "lname"
        private val KEY_TYPE = "type"
        private val KEY_NUMBER = "number"
        private val KEY_SHIPPING = "shipping"
        private val KEY_PRICE = "price"
    }

}
