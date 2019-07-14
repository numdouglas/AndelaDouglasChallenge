package com.example.user.andeladouglaschallenge;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_A extends AppCompatActivity {

    public static final String NAME = "Douglas Mbogo";
    public static final String TRACK = "Android";
    public static final String EMAIL = "salitosmbogz@gmail.com";
    public static final String PHONE_NUMBER = "+254741287087";
    public static final String COUNTRY = "Kenya";
    public static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Button abt_alc=findViewById(R.id.alc_info);
        Button abt_user=findViewById(R.id.user_info);
        ButtonAlcEar alcEar=new ButtonAlcEar();
        ButtonUserEar userEar=new ButtonUserEar();


        abt_alc.setOnClickListener(alcEar);
        abt_user.setOnClickListener(userEar);


    }



    private class ButtonAlcEar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Activity_B.class);
            getApplication().startActivity(intent);
        }
    }

    private class ButtonUserEar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Activity_C.class);
            intent.putExtra(USER, new UserDetails(NAME, TRACK, EMAIL, PHONE_NUMBER,
                    COUNTRY));
            getApplication().startActivity(intent);
        }
    }
}
