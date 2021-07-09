package com.example.responsi_mp3.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User userEntity);

    @Query
    ("SELECT * from users where email=(:email) and password=(:password)")
    User login(String email, String password);

    @Query
    ("SELECT * from users where email=(:email)")
    User recovery(String email);
}
