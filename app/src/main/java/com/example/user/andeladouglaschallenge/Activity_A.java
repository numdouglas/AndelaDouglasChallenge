package com.example.user.andeladouglaschallenge;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//This activity will host the home dashboard
public class Activity_A extends AppCompatActivity {
//constants in this case store my details
    public static final String NAME = "Douglas Mbogo Ntongai";
    public static final String TRACK = "Android";
    public static final String EMAIL = "salitosmbogz@gmail.com";
    public static final String PHONE_NUMBER = "+254741287087";
    public static final String COUNTRY = "Kenya";
    //this constant stores the parcelname
    public static final String PARCEL = "user";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //use associated layout
        setContentView(R.layout.activity_a);
        //load pointers to and inflate the two buttons in the layout
        Button abt_alc=findViewById(R.id.alc_info);
        Button abt_user=findViewById(R.id.user_info);
        //create references to the listeners for the two buttons
        ButtonAlcEar alcEar=new ButtonAlcEar();
        ButtonUserEar userEar=new ButtonUserEar();
        //and set them as their action listeners
        abt_alc.setOnClickListener(alcEar);
        abt_user.setOnClickListener(userEar);


    }


//declare this listener to listen for button one and switch to Activity_B when it's clicked
    private class ButtonAlcEar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Activity_B.class);
            startActivity(intent);
        }
    }

 //declare this listener to listen for button two, transfer objects between activities and switch to Activity_C
    private class ButtonUserEar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Activity_C.class);
            intent.putExtra(PARCEL, new UserDetails(NAME, TRACK, EMAIL, PHONE_NUMBER,
                    COUNTRY));
            startActivity(intent);
        }
    }
}