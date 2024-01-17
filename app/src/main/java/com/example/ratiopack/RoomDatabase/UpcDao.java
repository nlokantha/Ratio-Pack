package com.example.ratiopack.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UpcDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Upc upc);

    @Query("SELECT * FROM Upc")
    List<Upc> getAll();
}
