package com.example.androidfinal;

import androidx.room.*;
import java.util.List;


@Dao
public interface TeamDAO {

    @Query("Select * from Teams")
    List<Team> getTeams();

    @Insert
    void addTeam(Team team);

    @Update
    void updateTeam(Team team);


}