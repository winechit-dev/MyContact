package com.example.mynote.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mynote.DataAccess.ContactDao;
import com.example.mynote.Model.Contact;


@Database(entities = {Contact.class}, version =  1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static final String DATABASE_NAME = "MyNoteDatabase";
    private static MyDatabase myDatabase = null;


    public static MyDatabase getMyDatabase(Context context){
        if (myDatabase == null){

            myDatabase  = Room.databaseBuilder(context.getApplicationContext()
                                ,MyDatabase.class,DATABASE_NAME).build();

        }
        return myDatabase;
    }

}
