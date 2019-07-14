package com.example.user.andeladouglaschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_C extends AppCompatActivity {

    private static final String PARCEL = "user";
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflate view and view components
        setContentView(R.layout.activity__c);
        TextView name=findViewById(R.id.name_val);
        TextView track=findViewById(R.id.track_val);
        TextView email=findViewById(R.id.email_value);
        TextView phone_num=findViewById(R.id.phone_no_value);
        TextView country=findViewById(R.id.country_value);

        //load the bundle received from calling activity and assign its content to view
        Bundle data = getIntent().getExtras();
        user = data.getParcelable(PARCEL);
        name.setText(user.getName());
        track.setText(user.getTrack());
        email.setText(user.getEmail());
        phone_num.setText(user.getPhone_No());
        country.setText(user.getCountry());

    }


//save the parcel during configuration changes so its not recrerated
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(PARCEL,user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        user=savedInstanceState.getParcelable(PARCEL);
    }}