package com.example.androidfinal;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;


@Dao
public interface TeamDAO {

    @Query("Select * from Teams")
    LiveData<List<Team>> watchTeams();

    @Query("Select * from Teams")
    List<Team> getTeams();

    @Insert
    void addTeam(Team team);

    @Update
    void updateTeam(Team team);


}