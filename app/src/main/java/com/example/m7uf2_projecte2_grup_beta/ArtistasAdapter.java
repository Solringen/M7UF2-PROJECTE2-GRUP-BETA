package com.example.m7uf2_projecte2_grup_beta;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


class ArtistasAdapter extends FirestoreRecyclerAdapter<Artistas, ArtistasAdapter.ArtistasHolder> {

    // Classe "holder" que proporciona una referència a als components de cada element
    // de la llista. La declarem com a classe interna per millorar l'encapsulació de l'Adapter.
    public class ArtistasHolder extends RecyclerView.ViewHolder {
        public View element;
        public TextView tv01, tv02;
        public ImageView image01,image02;
        public RecyclerView esculturas;
        private RecyclerView rvSubItem;



        public ArtistasHolder(View itemView) {
            super(itemView);
            tv01 = (TextView) itemView.findViewById(R.id.tv_esculturas);
            tv02 = (TextView) itemView.findViewById(R.id.tv_cogNom);
            image01 = (ImageView) itemView.findViewById(R.id.i_artista);
            esculturas = (RecyclerView) itemView.findViewById(R.id.r_esculturas);
            rvSubItem = itemView.findViewById(R.id.r_esculturas);

        }
    }

    // Constructor de l'Adapter que reaprofita directament el constructor de la classe pare
    // que inicialitza l'Adapter amb l'objecte "Options" que ens passen (l'objecte options es
    // crea dins el mètode onCreate() de l'activity que conté el RecyclerView.
    public ArtistasAdapter(@NonNull FirestoreRecyclerOptions<Artistas> opcions) {
        super(opcions);
    }

    // Mètode que "lliga" els atributs dels objecte Alumne amb cadascun dels components gràfics
    // on els volem visualitzar dins de cada element del RecyclerView
    @Override
    protected void onBindViewHolder(@NonNull ArtistasHolder holder, int position, @NonNull Artistas art) {
        FirebaseFirestore db;
        List<String> data = new ArrayList<>();
        for (int i = 0; i<art.getEscultura().size();i++){
            data.add(art.getEscultura().get(i).getTitol());
        }
        db= FirebaseFirestore.getInstance();
        holder.tv01.setText(art.getNom());
        holder.tv02.setText(art.getCogNom());


        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        holder.rvSubItem.setFocusableInTouchMode(true);
        holder.rvSubItem.requestFocus();
        // Create sub item view adapter
        SubEsculturasAdapter subEsculturasAdapter = new SubEsculturasAdapter(data);

        holder.rvSubItem.setLayoutManager(layoutManager);
        holder.rvSubItem.setAdapter(subEsculturasAdapter);
        // Hem de lligar la primera foto que tenim al List de fotos de l'objecte amb l'ImageView
        // que tenim en cadascun dels elements del RecyclerView
        if (art.getFotos() != null && art.getFotos().size() > 0) {
            // Agafem la primera foto de l'array de fotos i la posem a l'ImageView
            Bitmap bMap = BitmapFactory.decodeByteArray(
                    art.getFotos().get(0).toBytes(), 0, art.getFotos().get(0).toBytes().length);
            holder.image01.setImageBitmap(bMap);
        }
        else {
            // Si no hi ha fotos hi posem una imatge per defecte.
            holder.image01.setImageResource(R.drawable.foto1);
        }


    }

    // Mètode que fa l'inflate (conversió de XML a objectes) de cada element del RecyclerView a partir
    // del disseny en XML que tenim a res/layout/alumne.xml.
    @NonNull
    @Override
    public ArtistasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista5_artistas, parent, false);
        return new ArtistasHolder(vista);
    }



}
