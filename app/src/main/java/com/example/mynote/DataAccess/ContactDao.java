package com.example.mynote.DataAccess;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mynote.Model.Contact;

import java.util.List;

@android.arch.persistence.room.Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> getAllContact();

    @Query("SELECT * FROM contact WHERE name LIKE :name ")
    Contact findbyName (String name);

    @Insert
    void insertContact(Contact contact);
    @Insert
    void insertAllContact(Contact...contacts);


    @Delete
    void deleteContact(Contact contact);
    @Delete
    void deleteAllContact(Contact...contacts);
}
