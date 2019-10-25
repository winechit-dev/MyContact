package com.example.mynote.ContactServices;

import android.content.Context;

import com.example.mynote.DataAccess.ContactDao;
import com.example.mynote.Database.MyDatabase;
import com.example.mynote.Model.Contact;

import java.util.List;

public class RetriveContactServices implements ContactServices {
    private ContactDao contactDao;


    public RetriveContactServices(Context context){

        contactDao = MyDatabase.getMyDatabase(context).contactDao();
    }
    @Override
    public List<Contact> getAllContacts() {
        return contactDao.getAllContact();
    }


    @Override
    public Contact getContact(String name) {
        return (Contact) contactDao.findbyName(name);
    }

    @Override
    public void insertContact(Contact contact) {
        contactDao.insertContact(contact);
    }

    @Override
    public void insertAllContacts(Contact...contacts) {
        contactDao.insertAllContact(contacts);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactDao.deleteContact(contact);
    }

    @Override
    public void deleteAllContact(Contact...contacts) {
        contactDao.deleteAllContact(contacts);
    }
}
