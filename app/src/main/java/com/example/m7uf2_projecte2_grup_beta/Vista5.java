package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Vista5 extends AppCompatActivity {
    BottomNavigationView bnvBotonera;
    RecyclerView rvArtista;
    RecyclerView.LayoutManager alumnesLayout;
    FirebaseFirestore db;
    public static ArtistasAdapter adapterArtistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista5);

        rvArtista = findViewById(R.id.rvArtistas);


        // Obtenim les referències necessàries als components de la interfície.
        bnvBotonera = findViewById(R.id.bnvBotoneraVista5);

        bnvBotonera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(Vista5.this, item.toString(), Toast.LENGTH_LONG).show();
                if(item.getTitle().equals("Fundació")){
                    Intent intent = new Intent(Vista5.this, Vista2.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Mapa")){
                    Intent intent = new Intent(Vista5.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Escultures")){
                    Intent intent = new Intent(Vista5.this, Vista4.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
        bnvBotonera.setSelectedItemId(R.id.itVistaArtistes);

        // Obtenim una instància d'accés a la base de dades Firestore.
        db = FirebaseFirestore.getInstance();

        // Si volem llista, activem el new LinearLayout...(); si volem grid, activem
        // el new GridLayout...(), indicant el nombre de columnes.
        //alumnesLayout = new GridLayoutManager(this, 2);
        alumnesLayout = new LinearLayoutManager(this);
        rvArtista.setLayoutManager(alumnesLayout);

        // Preparem la consulta que obtindrà les dades a visualitzar en el RecyclerView.
        Query consulta = db.collection("Artistas").limit(50);

        // Preparem l'objecte "Options" que ens ha de permetre crear l'adapter. Aquest objecte
        // defineix, entre altres aspectes, la consulta amb el tipus d'objecte que retornarà
        // aquesta consulta (en el nostre cas, Alumne).
        FirestoreRecyclerOptions<Artistas> opcions =
                new FirestoreRecyclerOptions
                        .Builder<Artistas>()
                        .setQuery(consulta, Artistas.class)
                        .build();
        // Creem l'objecte Adapter passant-li l'objecte Options al constructor.
        adapterArtistas = new ArtistasAdapter(opcions);

        // Associem l'adapter creat amb el RecyclerView que tenim a la vista.
        rvArtista.setAdapter(adapterArtistas);
    }

    @Override
    // En l'esdeveniment onStart() de l'activity activem el mode "Listening" del Adapter del
    // RecyclerView perquè actualitzi automàticament el contingut si detecta actualitzacions
    // a la base de dades.
    protected void onStart() {
        super.onStart();
        adapterArtistas.startListening();
    }

    // Si l'activity queda oculta o finalitza, aturem el mode "Listening" de l'Adapter ja
    // que consumeix recursos de forma innecessària si resta activat mentre l'activity no
    // no es veu.
    @Override
    protected void onStop() {
        super.onStop();
        adapterArtistas.stopListening();
    }
}