package com.example.m7uf2_projecte2_grup_beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Vista4 extends AppCompatActivity {

    RecyclerView rvAlumnes;
    RecyclerView.LayoutManager alumnesLayout;
    FirebaseFirestore db;
    public static EsculturasAdapterFirestore adapterEsculturas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista4);
        rvAlumnes = findViewById(R.id.rvAlumnes);

        // Obtenim una instància d'accés a la base de dades Firestore.
        db = FirebaseFirestore.getInstance();

        // Si volem llista, activem el new LinearLayout...(); si volem grid, activem
        // el new GridLayout...(), indicant el nombre de columnes.
        //alumnesLayout = new LinearLayoutManager(this);
        alumnesLayout = new GridLayoutManager(this, 2);
        rvAlumnes.setLayoutManager(alumnesLayout);

        // Preparem la consulta que obtindrà les dades a visualitzar en el RecyclerView.
        Query consulta = db.collection("Esculturas").limit(50);

        // Preparem l'objecte "Options" que ens ha de permetre crear l'adapter. Aquest objecte
        // defineix, entre altres aspectes, la consulta amb el tipus d'objecte que retornarà
        // aquesta consulta (en el nostre cas, Alumne).
        FirestoreRecyclerOptions<Esculturas> opcions =
                new FirestoreRecyclerOptions
                        .Builder<Esculturas>()
                        .setQuery(consulta, Esculturas.class)
                        .build();
        // Creem l'objecte Adapter passant-li l'objecte Options al constructor.
        adapterEsculturas = new EsculturasAdapterFirestore(opcions);

        // Associem l'adapter creat amb el RecyclerView que tenim a la vista.
        rvAlumnes.setAdapter(adapterEsculturas);

    }

    @Override
    // En l'esdeveniment onStart() de l'activity activem el mode "Listening" del Adapter del
    // RecyclerView perquè actualitzi automàticament el contingut si detecta actualitzacions
    // a la base de dades.
    protected void onStart() {
        super.onStart();
        adapterEsculturas.startListening();
    }

    // Si l'activity queda oculta o finalitza, aturem el mode "Listening" de l'Adapter ja
    // que consumeix recursos de forma innecessària si resta activat mentre l'activity no
    // no es veu.
    @Override
    protected void onStop() {
        super.onStop();
        adapterEsculturas.stopListening();
    }

}