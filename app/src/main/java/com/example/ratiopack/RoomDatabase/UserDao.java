package com.example.ratiopack.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE upcNumber = :upcNumber")
    List<User> getbyUpc(String upcNumber);

    @Query("UPDATE user SET quantity = :newQuantity WHERE upcNumber = :upcNumber AND color = :color AND size = :size")
    void updateQuantity(String upcNumber, String color, String size, String newQuantity);

    @Query("UPDATE user SET quantity= :newQuantity WHERE id=:id ")
    void update(int id,String newQuantity);

    @Query("UPDATE user SET barCode = :newBarcode WHERE upcNumber = :upcNumber AND color = :color AND size = :size")
    void updateBarcode(String upcNumber, String color, String size, String newBarcode);


}
