package com.example.m7uf2_projecte2_grup_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Vista1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista1);

        //Codi que ens permet fer que la vista inicial duri els segons que nosaltres vulguem.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Vista1.this, Vista2.class);
                startActivity(intent);
                finish();
            }
        },1000);

    }
}