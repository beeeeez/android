package com.example.inclassweek6;


import androidx.room.*;
import com.example.inclassweek6.Account;

import java.util.List;


@Dao
public interface BankAccountDao {

    @Query("SELECT * FROM accounts")
    List<Account> getAllAccounts();

    @Query("SELECT * FROM accounts WHERE name= :jimmy")
    List<Account> getAccountsByName(String jimmy);

    @Insert
    void addAccount(Account account);


    @Update
    void updateaccount(Account account);




}
