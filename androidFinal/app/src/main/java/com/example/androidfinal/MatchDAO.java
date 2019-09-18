package com.example.androidfinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


    @Dao
    public interface MatchDAO {

        @Query("Select * from Matches")
        LiveData<List<Match>> watchMatch();

        @Query("Select * from Matches")
        List<Team> getMatches();

        @Insert
        void addMatch(Match match);

        @Update
        void updateMatch(Match match);


    }

