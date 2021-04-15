package com.example.m7uf2_projecte2_grup_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Vista2 extends AppCompatActivity {

    BottomNavigationView bnvBotonera;
    Toolbar tbFundacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista2);

        tbFundacio =findViewById(R.id.tb_fundacion);
        setSupportActionBar(tbFundacio);

        // Obtenim les referències necessàries als components de la interfície.
        bnvBotonera = findViewById(R.id.bnvBotonera);

        bnvBotonera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(Vista2.this, item.toString(), Toast.LENGTH_LONG).show();
                if(item.getTitle().equals("Escultures")){
                    Intent intent = new Intent(Vista2.this, Vista4.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Mapa")){
                    Intent intent = new Intent(Vista2.this, Vista3.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Artistes")){
                    Intent intent = new Intent(Vista2.this, Vista5.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.botonera_arriba,menu);
        return true;
    }


}