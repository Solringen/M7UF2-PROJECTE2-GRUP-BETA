package com.example.m7uf2_projecte2_grup_beta;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


class EsculturasAdapterFirestore extends FirestoreRecyclerAdapter<Esculturas, EsculturasAdapterFirestore.EsculturasHolder> {

    // Classe "holder" que proporciona una referència a als components de cada element
    // de la llista. La declarem com a classe interna per millorar l'encapsulació de l'Adapter.
    public class EsculturasHolder extends RecyclerView.ViewHolder {
        public View element;
        public TextView tv01, tv02;
        public ImageView image01,image02;


        public EsculturasHolder(View itemView) {
            super(itemView);
            tv01 = (TextView) itemView.findViewById(R.id.tv01);
            image01 = (ImageView) itemView.findViewById(R.id.image01);

        }
    }

    // Constructor de l'Adapter que reaprofita directament el constructor de la classe pare
    // que inicialitza l'Adapter amb l'objecte "Options" que ens passen (l'objecte options es
    // crea dins el mètode onCreate() de l'activity que conté el RecyclerView.
    public EsculturasAdapterFirestore(@NonNull FirestoreRecyclerOptions<Esculturas> opcions) {
        super(opcions);
    }

    // Mètode que "lliga" els atributs dels objecte Alumne amb cadascun dels components gràfics
    // on els volem visualitzar dins de cada element del RecyclerView
    @Override
    protected void onBindViewHolder(@NonNull EsculturasHolder holder, int position, @NonNull Esculturas esc) {
        holder.tv01.setText(esc.getTitol());


        // Hem de lligar la primera foto que tenim al List de fotos de l'objecte amb l'ImageView
        // que tenim en cadascun dels elements del RecyclerView
        if (esc.getFotos() != null && esc.getFotos().size() > 0) {
            // Agafem la primera foto de l'array de fotos i la posem a l'ImageView
            Bitmap bMap = BitmapFactory.decodeByteArray(
                    esc.getFotos().get(0).toBytes(), 0, esc.getFotos().get(0).toBytes().length);
            holder.image01.setImageBitmap(bMap);
        }
        else {
            // Si no hi ha fotos hi posem una imatge per defecte.
            holder.image01.setImageResource(R.drawable.appbar_iconaanar);
        }

    }

    // Mètode que fa l'inflate (conversió de XML a objectes) de cada element del RecyclerView a partir
    // del disseny en XML que tenim a res/layout/alumne.xml.
    @NonNull
    @Override
    public EsculturasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista4_estructura, parent, false);
        return new EsculturasHolder(vista);
    }
}
