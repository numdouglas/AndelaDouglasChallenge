package com.example.user.andeladouglaschallenge;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;

public class Activity_A extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Button abt_alc=findViewById(R.id.alc_info);
        buttonAlcEar alcEar=new buttonAlcEar();
        abt_alc.setOnClickListener(alcEar);


    }

    private class buttonAlcEar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Activity_B.class);
            getApplication().startActivity(intent);
        }
    }
}
