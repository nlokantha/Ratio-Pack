package com.example.ratiopack.RoomDatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Upc.class,parentColumns = "upcNumber",childColumns = "upcNumber",
        onDelete = ForeignKey.CASCADE))
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id=0;
    String color;
    String size;
    String quantity;
    String barCode;
    String upcNumber;
//    Boolean isdone;

    public User() {
    }

//    public Boolean getIsdone() {
//        return isdone;
//    }
//
//    public void setIsdone(Boolean isdone) {
//        this.isdone = isdone;
//    }

    public User(int id, String color, String size, String quantity, String barCode, String upcNumber) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.barCode = barCode;
        this.upcNumber = upcNumber;
    }

    public User(String color, String size, String quantity, String barCode) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.barCode = barCode;
    }

    public User(String color, String size, String quantity, String barCode, String upcNumber) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.barCode = barCode;
        this.upcNumber = upcNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUpcNumber() {
        return upcNumber;
    }

    public void setUpcNumber(String upcNumber) {
        this.upcNumber = upcNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", quantity='" + quantity + '\'' +
                ", barCode='" + barCode + '\'' +
                ", upcNumber='" + upcNumber + '\'' +
                '}';
    }
}
