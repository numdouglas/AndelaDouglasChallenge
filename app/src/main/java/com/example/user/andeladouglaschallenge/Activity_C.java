package com.example.user.andeladouglaschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_C extends AppCompatActivity {

    private static final String USER = "user";
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__c);
        TextView name=findViewById(R.id.name_val);
        TextView track=findViewById(R.id.track_val);
        TextView email=findViewById(R.id.email_value);
        TextView phone_num=findViewById(R.id.phone_no_value);
        TextView country=findViewById(R.id.country_value);
        Bundle data = getIntent().getExtras();

        user = data.getParcelable(USER);
        name.setText(user.getName());
        track.setText(user.getTrack());
        email.setText(user.getEmail());
        phone_num.setText(user.getPhone_No());
        country.setText(user.getCountry());

    }



    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(USER,user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        user=savedInstanceState.getParcelable(USER);
    }




}
