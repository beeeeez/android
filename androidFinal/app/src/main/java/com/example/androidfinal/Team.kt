package com.example.androidfinal

import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Teams")
class Team{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0
    @ColumnInfo(name="name")
    var name: String = ""
    @ColumnInfo(name="city")
    var city: String = ""
    @ColumnInfo(name="record")
    var record: String = ""

    constructor()
    constructor(name: String, city: String, record: String){
        this.name=name
        this.city=city
        this.record=record
    }
}