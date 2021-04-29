package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Blob;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EjemploBaseDeDatos extends AppCompatActivity {
    private Button btInsertar,btEsculturas,btArtista;
    private TextView tvContenido;
    FirebaseFirestore db;
    private Blob foto;
    private Blob audio = Blob.fromBytes(new byte[]{3, 5, 1, 2, 7, 3, 9, 2, 3, 5});
    private List<String> obras = new ArrayList<String>();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_base_de_datos);
        obras.add("E01");
        btInsertar = findViewById(R.id.btInsertar);
        btArtista = findViewById(R.id.btArtistas);
        btEsculturas = findViewById(R.id.btEsculturas);
        tvContenido =findViewById(R.id.tvContenido);


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.botonera_iconaartistes);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        foto = Blob.fromBytes(baos.toByteArray());






        db=FirebaseFirestore.getInstance();
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Artistas a1 = new Artistas("A01", "Josep Sobrado", "Balboa",audio,foto, "esto es bigrafia de josep", "corrent artistic de josep,", obras);
                Esculturas e1 = new Esculturas("E03","Ciborra","Monistrol de Calders",audio, foto,"descrpcion de obra",a1);
                db.collection("Esculturas")
                        .document("E03")
                        .set(e1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                Toast.makeText(EjemploBaseDeDatos.this,"Insercio correcta",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EjemploBaseDeDatos.this,"La insercio ha fallat"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        btArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("Artistas")
                        .document("A01")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()) {
                                    Artistas a2 = documentSnapshot.toObject(Artistas.class);
                                    tvContenido.setText(a2.toString());
                                }
                                else{
                                    Toast.makeText(EjemploBaseDeDatos.this, "Artista no existe", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        btEsculturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("Esculturas")
                        .document("E01")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()) {
                                    Esculturas e1 = documentSnapshot.toObject(Esculturas.class);
                                    tvContenido.setText(e1.toString());
                                }
                                else{
                                    Toast.makeText(EjemploBaseDeDatos.this, "Escultura no existe", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }


}