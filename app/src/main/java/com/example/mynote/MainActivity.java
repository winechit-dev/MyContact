package com.example.mynote;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynote.ContactServices.RetriveContactServices;
import com.example.mynote.Model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    private CardView contactCardView;
    private RetriveContactServices services;
    private ProgressDialog progressDialog;
    private ContactsAdapter adapter;
    List<Contact> contacts;
    private Integer contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        initViews();
        services    = new RetriveContactServices(this);
        btnAdd.setOnClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        getContacts();


    }

    private void initViews() {
        recyclerView    = findViewById(R.id.contactsView);
        btnAdd          = findViewById(R.id.btn_add);
        contactCardView = recyclerView.findViewById(R.id.contact_cardView);
    }


    private void getContacts() {

        class RetriveAllContacts extends AsyncTask<Void,Void, List<Contact>>{

            @Override
            protected List<Contact> doInBackground(Void... voids) {

                 contacts = services.getAllContacts();

                return contacts;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                super.onPostExecute(contacts);

                adapter = new ContactsAdapter(MainActivity.this, contacts, new ContactsAdapter.OnItemClick() {
                    @Override
                    public void onItemClicked(int id) {
                        goDetailed(id);
                    }
                }, new ContactsAdapter.OnLongPress() {
                    @Override
                    public void onLongPress(int id) {
                        longPress(id);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        }

        RetriveAllContacts retriveAllContacts   = new RetriveAllContacts();
        retriveAllContacts.execute();

    }




    private void deleteContact() {

        if (contactID != null) {
            class DeleteContact extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {
                    services.deleteContact(contacts.get(contactID));
                    contactID = null;
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    recreate();
                }
            }


            DeleteContact deleteContact = new DeleteContact();
            deleteContact.execute();
        }else Toast.makeText(MainActivity.this,"Please Select to Delete",Toast.LENGTH_SHORT).show();
    }




    private void longPress(int id) {

        contactID   = id;
        Toast.makeText(MainActivity.this,"On Long Pressed   "+id,Toast.LENGTH_SHORT).show();
        }


    private void goDetailed(int id) {

        Contact contact = contacts.get(id);
        Intent intent   = new Intent(MainActivity.this,Detailed.class);
        intent.putExtra("contact",contact);
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add){
            startActivity(new Intent(MainActivity.this, AddContact.class));

        }
    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater   = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.deleteContact : { deleteContact();}break;
        }

        return super.onOptionsItemSelected(item);


    }


}
