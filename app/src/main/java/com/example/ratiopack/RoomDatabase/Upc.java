package com.example.ratiopack.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Upc {

    @PrimaryKey @NonNull
    String upcNumber;

    public Upc(@NonNull String upcNumber) {
        this.upcNumber = upcNumber;
    }

    @Override
    public String toString() {
        return "Upc{" +
                "upcNumber='" + upcNumber + '\'' +
                '}';
    }

    public String getUpcNumber() {
        return upcNumber;
    }
}
