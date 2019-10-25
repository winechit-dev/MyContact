package com.example.mynote.ContactServices;


import com.example.mynote.Model.Contact;

import java.util.List;

public interface ContactServices {

    List<Contact> getAllContacts();

    Contact getContact(String name);

    void insertContact(Contact contact);

    void insertAllContacts(Contact...contacts);

    void deleteContact(Contact contact);

    void deleteAllContact(Contact...contacts);
}
