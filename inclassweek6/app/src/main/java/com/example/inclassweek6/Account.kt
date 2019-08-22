package com.example.inclassweek6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="accounts")
class Account{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0
    @ColumnInfo(name="name")
    var name: String = ""
    @ColumnInfo(name="type")
    var type: String = ""
    //private variables
    // getting ID
    // setting id

    // getting name
    // setting name

    // getting phone number
    // setting phone number




    // Empty constructor
    constructor() {

    }

    // constructor
    constructor(id: Int, name: String, type: String) {
        this.id = id
        this.name = name
        this.type = type
    }

    // constructor
    constructor(name: String, type: String) {
        this.name = name
        this.type = type
    }
}
