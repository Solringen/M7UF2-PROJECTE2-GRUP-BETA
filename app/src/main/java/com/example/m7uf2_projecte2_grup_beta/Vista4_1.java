package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class Vista4_1 extends AppCompatActivity {
    JustifyTextView artistaDesc;
    BottomNavigationView bnvBotonera;
    TextView tv_titulo,tv_material,tv_peso,tv_any,tv_nombre;
    Esculturas e1;
    ImageView ivEs,ivAr;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista4_1);
        artistaDesc = findViewById(R.id.desc_artista);
        tv_titulo = findViewById(R.id.tv_titulo);
        tv_material = findViewById(R.id.tv_material);
        tv_peso = findViewById(R.id.tv_peso);
        tv_any = findViewById(R.id.tv_any);
        tv_nombre = findViewById(R.id.tv_arnom);
        ivEs=findViewById(R.id.img_Es);
        ivAr=findViewById(R.id.img_Ar);
        bnvBotonera = findViewById(R.id.bnvBotoneraVista4_1);
        db=FirebaseFirestore.getInstance();
        ConsultaEscultura();

        bnvBotonera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(Vista4_1.this, item.toString(), Toast.LENGTH_LONG).show();
                if(item.getTitle().equals("Fundació")){
                    Intent intent = new Intent(Vista4_1.this, Vista2.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Mapa")){
                    Intent intent = new Intent(Vista4_1.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Artistes")){
                    Intent intent = new Intent(Vista4_1.this, Vista5.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
        bnvBotonera.setSelectedItemId(R.id.itVistaEscultures);



    }

    public void  ConsultaEscultura(){
        db.collection("Esculturas")
                .document(getIntent().getExtras().getString("Es"))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            e1 = documentSnapshot.toObject(Esculturas.class);
                            Bitmap bMap = BitmapFactory.decodeByteArray(
                                   e1.getFotos().get(0).toBytes(), 0, e1.getFotos().get(0).toBytes().length);
                            tv_titulo.setText(e1.getTitol());
                            tv_material.setText(tv_material.getText() + e1.getMaterial());
                            tv_peso.setText(tv_peso.getText() + e1.getPes());
                            tv_any.setText(tv_any.getText() + e1.getAny());

                            ConsultaArtistas(e1.getArtista());
                            ivEs.setImageBitmap(bMap);
                        }
                        else{
                            Toast.makeText(Vista4_1.this, "Escultura no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void  ConsultaArtistas(String s) {
        db.collection("Artistas")
                .document(s)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Artistas a2 = documentSnapshot.toObject(Artistas.class);
                            Bitmap bMap = BitmapFactory.decodeByteArray(
                                    a2.getFotos().get(0).toBytes(), 0, a2.getFotos().get(0).toBytes().length);
                            ivAr.setImageBitmap(bMap);
                            tv_nombre.setText(a2.getNom() + " "+a2.getCogNom());
                            artistaDesc.setText(a2.getBiografia() + "                                              ");
                        } else {
                            Toast.makeText(Vista4_1.this, "Artista no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}