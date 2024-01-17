package com.example.ratiopack.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,Upc.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase database;
    private static String DATABASE_NAME="database";

    public synchronized static UserDatabase getInstance(Context context){
        if (database == null){
            database= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract UserDao userDao();
    public abstract UpcDao upcDao();
}
