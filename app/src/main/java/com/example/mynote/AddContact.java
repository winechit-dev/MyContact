package com.example.mynote;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.mynote.ContactServices.RetriveContactServices;
import com.example.mynote.Model.Contact;
import com.example.mynote.Utilites.CheckEditTexts;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    TextInputEditText name,phone, email, address, city;
    TextInputEditText [] fields ;
    RetriveContactServices services ;
    CheckEditTexts  checkEditTexts ;


    String _name,_phone,_email,_address,_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.create_contact);
        setSupportActionBar(toolbar);


        initViews();
        checkEditTexts  = new CheckEditTexts();
        services   = new RetriveContactServices(this);
    }

    private void initViews() {
        name    = findViewById(R.id.name_edt);
        phone   = findViewById(R.id.phone_edt);
        email   = findViewById(R.id.email_edt);
        address = findViewById(R.id.address_edt);
        city    = findViewById(R.id.city_edt);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.save  : { saveContact();}break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    private void saveContact() {
         _name     = name.getText().toString();
         _phone    = phone.getText().toString();
         _email    = email.getText().toString();
         _address  = address.getText().toString();
         _city     = city.getText().toString();
        fields= new TextInputEditText[]{name,phone,email,address,city};

        if (checkEditTexts.check(fields)){ goSave(); }
     }



    private void goSave() {
        class SaveContact extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                Contact contact = new Contact(_name,_phone,_email,_address,_city);

                services.insertContact(contact);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(AddContact.this,"Saved!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddContact.this,MainActivity.class));
                finish();
            }


        }

        SaveContact saveContact = new SaveContact();
        saveContact.execute();
    }





}
