package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
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
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter  {

    private GoogleMap mMap;
    BottomNavigationView bnvBotonera;

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
                if(item.getTitle().equals("Fundació")){
                    Intent intent = new Intent(MapsActivity.this, Vista2.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Escultures")){
                    Intent intent = new Intent(MapsActivity.this, Vista4.class);
                    startActivity(intent);
                    finish();
                }

                else if(item.getTitle().equals("Artistes")){
                    Intent intent = new Intent(MapsActivity.this, Vista5.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
        bnvBotonera.setSelectedItemId(R.id.itVistaMapa);

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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacio, 50.0f));
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

    private View prepareInfoView(Marker marker){
        //prepare InfoView programmatically

        LinearLayout infoView = new LinearLayout(MapsActivity.this);
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView TOPInfoLat = new TextView(MapsActivity.this);
        TOPInfoLat.setText("Ajuntament de monistrol");

        TOPInfoLat.setTextSize(20);
        TOPInfoLat.setGravity(Gravity.CENTER);
        infoView.setOrientation(LinearLayout.VERTICAL);

        infoView.setLayoutParams(infoViewParams);
        infoView.addView(TOPInfoLat);

        ImageView infoImageView = new ImageView(MapsActivity.this);
        infoImageView.setImageResource(R.drawable.foto1);
        infoView.addView(infoImageView);

        LinearLayout subInfoView = new LinearLayout(MapsActivity.this);



        TextView subInfoLat = new TextView(MapsActivity.this);
        subInfoLat.setText("Veure fitxa");
        subInfoLat.setTextSize(15);
        infoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, Vista5.class);
                startActivity(intent);
                finish();
            }
        });
        subInfoLat.setGravity(Gravity.CENTER);


        subInfoView.addView(subInfoLat);
        infoView.addView(subInfoView);

        return infoView;
    }
}