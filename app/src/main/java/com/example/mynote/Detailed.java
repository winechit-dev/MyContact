package com.example.mynote;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynote.Model.Contact;

import java.util.List;

public class Detailed extends AppCompatActivity implements View.OnClickListener {
    private static final int RESQUEST_CALL = 1;
    private TextView name ,phone, email, address, city;
    private Contact detailedContact;
    private FloatingActionButton btnMakeCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Toolbar toolbar = findViewById(R.id.detailed_toolbar);
        toolbar.setTitle(R.string.contact_details);
        setSupportActionBar(toolbar);
        initViews();

        Intent intent   = getIntent();
        detailedContact = (Contact) intent.getSerializableExtra("contact");
        setData();
        btnMakeCall.setOnClickListener(this);


    }



    private void makeCall() {

        String number = detailedContact.getPhone();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Detailed.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Detailed.this, new String[]{Manifest.permission.CALL_PHONE}, RESQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RESQUEST_CALL){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                makeCall();
            }else
            {Toast.makeText(Detailed.this,"Permission Denied",Toast.LENGTH_SHORT).show();}
        }
    }

    private void setData() {

        name.setText(detailedContact.getName());
        phone.setText(detailedContact.getPhone());
        email.setText(detailedContact.getEmail());
        address.setText(detailedContact.getAddress());
        city.setText(detailedContact.getCity());


    }

    private void initViews() {
        name        = findViewById(R.id.contact_name);
        phone       = findViewById(R.id.contact_phone);
        email       = findViewById(R.id.contact_email);
        address     = findViewById(R.id.contact_address);
        city        = findViewById(R.id.contact_city);
        btnMakeCall = findViewById(R.id.makeCall);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.makeCall  :{ makeCall();}break;
        }
    }
}
