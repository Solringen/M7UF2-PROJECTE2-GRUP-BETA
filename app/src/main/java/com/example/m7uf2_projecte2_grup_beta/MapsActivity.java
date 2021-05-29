package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {
    FirebaseFirestore db;
    private GoogleMap mMap;
    BottomNavigationView bnvBotonera;
    Esculturas e1;
    TextView TOPInfoLat,subInfoLat;
    ImageView infoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GoogleMapOptions options = new GoogleMapOptions().zoomControlsEnabled(true);
        // Obtenim les referències necessàries als components de la interfície.
        bnvBotonera = findViewById(R.id.bnvBotoneraVistaMaps);

        bnvBotonera.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MapsActivity.this, item.toString(), Toast.LENGTH_LONG).show();
                if (item.getTitle().equals("Fundació")) {
                    Intent intent = new Intent(MapsActivity.this, Vista2.class);
                    startActivity(intent);
                    finish();
                } else if (item.getTitle().equals("Escultures")) {
                    Intent intent = new Intent(MapsActivity.this, Vista4.class);
                    startActivity(intent);
                    finish();
                } else if (item.getTitle().equals("Artistes")) {
                    Intent intent = new Intent(MapsActivity.this, Vista5.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
        bnvBotonera.setSelectedItemId(R.id.itVistaMapa);
        db= FirebaseFirestore.getInstance();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in our desired ubication and move the camera
        LatLng ubicacio = new LatLng(41.60985061194154, 1.8427093109114916);
        mMap.addMarker(new MarkerOptions().position(ubicacio).title("Ajuntament de Monistrol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacio));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacio, 20.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setInfoWindowAdapter(this);
    }

    public View getInfoWindow(Marker marker) {
        return null;
        //return prepareInfoView(marker);
    }


    public View getInfoContents(Marker marker) {
        //return null;
        return prepareInfoView(marker);

    }

    public View prepareInfoView(Marker marker) {
        //prepare InfoView programmatically

        LinearLayout infoView = new LinearLayout(MapsActivity.this);
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TOPInfoLat = new TextView(MapsActivity.this);
        TOPInfoLat.setText("AA");

        TOPInfoLat.setTextSize(20);
        TOPInfoLat.setGravity(Gravity.CENTER);
        infoView.setOrientation(LinearLayout.VERTICAL);

        infoView.setLayoutParams(infoViewParams);
        infoView.addView(TOPInfoLat);

        infoImageView = new ImageView(MapsActivity.this);
        infoImageView.setImageResource(R.drawable.foto1);
        infoView.addView(infoImageView);

        LinearLayout subInfoView = new LinearLayout(MapsActivity.this);


        subInfoLat = new TextView(MapsActivity.this);
        subInfoLat.setTextSize(15);
        ConsultaEscultura();
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, Vista4_1.class);
                intent.putExtra("Es", "E01");
                startActivity(intent);
            }
        });
        subInfoLat.setGravity(Gravity.CENTER);


        subInfoView.addView(subInfoLat);
        infoView.addView(subInfoView);

        return infoView;
    }
    public void  ConsultaEscultura(){
        db.collection("Esculturas")
                .document("E01")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            Esculturas e1 = documentSnapshot.toObject(Esculturas.class);
                            Bitmap bMap = BitmapFactory.decodeByteArray(
                                    e1.getFotos().get(0).toBytes(), 0, e1.getFotos().get(0).toBytes().length);
                            TOPInfoLat.setText(e1.getTitol());
                            infoImageView.setImageBitmap(bMap);
                            ConsultaArtistas(e1.getId());

                        }
                       /* else{
                            Toast.makeText(EjemploBaseDeDatos.this, "Escultura no existe", Toast.LENGTH_LONG).show();
                        }*/
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
                            subInfoLat.setText(a2.getNom() + " "+a2.getCogNom());
                        } else {
                            Toast.makeText(MapsActivity.this, "Artista no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}