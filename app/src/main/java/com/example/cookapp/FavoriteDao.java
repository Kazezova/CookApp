package com.example.cookapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    List<Favorite> getFavorites();

    @Query("SELECT * FROM favorite WHERE name=:name")
    Favorite getByName(String name);

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
