package com.example.androidfinal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Matches")
class Match {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "home")
    var home: String = ""
    @ColumnInfo(name = "away")
    var away: String = ""
    @ColumnInfo(name = "homeScore")
    var homeScore: Int = 0
    @ColumnInfo(name = "awayScore")
    var awayScore: Int = 0
    @ColumnInfo(name = "finished")
    var finished: Int = 0


    constructor()
    constructor(home: String, away: String, homeScore: Int, awayScore: Int, finished: Int) {
        this.home = home
        this.away = away
        this.homeScore = homeScore
        this.awayScore = awayScore
        this.finished = finished
    }

}