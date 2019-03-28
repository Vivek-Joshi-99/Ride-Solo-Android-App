package com.example.dell.ridesolo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

public class MapsActivity extends FragmentActivity  implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnInfoWindowClickListener{
    private GoogleMap mMap;
    private LocationManager locationManager;
    public LocationListener locationListener;
    TextView ed1, ed2;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    GoogleSignInClient mGoogleSignInClient;




    private static final LatLng v1= new LatLng(18.975655, 72.836270

    );
    private static LatLng v2 = new LatLng(19.229707, 72.845865

    );

    private static LatLng v3 = new LatLng(19.101163, 72.841802

    );

    private static LatLng v4 = new LatLng(19.087455, 72.837267

    );

    private static LatLng v5 = new LatLng(19.113000, 72.824049

    );
    private static LatLng v6 = new LatLng(19.113026, 72.837396

    );


    private Marker mv1;
    private Marker mv2;
    private Marker mv3;
    private Marker mv4;
    private Marker mv5;
    private Marker mv6;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // MAPS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();




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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setInfoWindowAdapter(new CustomInfoWindow(getApplicationContext()));



        LatLng Mumbai = new LatLng(19.0760, 72.8777);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mumbai, 11));


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());


            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //check for location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            // Write you code here if permission already given.

        }
        Location startLoc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mMap.setMyLocationEnabled(true);

        Location t1 = new Location(LocationManager.GPS_PROVIDER);
        t1.setLongitude(v1.longitude);
        t1.setLatitude(v1.latitude);

        double d1 = round(((startLoc.distanceTo(t1)) / 1000) * 100.0) / 100.0;
        String s1 = String.valueOf(d1);
        mv1 = mMap.addMarker(new MarkerOptions().position(v1).title("Danny Hiramanek").snippet("Distance : " + s1 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv1.setTag(0);



        Location t2 = new Location(LocationManager.GPS_PROVIDER);
        t2.setLongitude(v2.longitude);
        t2.setLatitude(v2.latitude);
        double d2 = round(((startLoc.distanceTo(t2)) / 1000) * 100.0) / 100.0;
        String s2 = String.valueOf(d2);
        mv2 = mMap.addMarker(new MarkerOptions().position(v2).title("Tanay Maheshwari").snippet("Distance : " + s2 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv2.setTag(1);


        Location t3 = new Location(LocationManager.GPS_PROVIDER);
        t3.setLongitude(v3.longitude);
        t3.setLatitude(v3.latitude);
        double d3 = round(((startLoc.distanceTo(t3)) / 1000) * 100.0) / 100.0;
        String s3 = String.valueOf(d3);
        mv3 = mMap.addMarker(new MarkerOptions().position(v3).title("Dhruvin Mehta").snippet("Distance : " + s3 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv3.setTag(2);

        Location t4 = new Location(LocationManager.GPS_PROVIDER);
        t4.setLongitude(v4.longitude);
        t4.setLatitude(v4.latitude);
        double d4 = round(((startLoc.distanceTo(t4)) / 1000) * 100.0) / 100.0;
        String s4 = String.valueOf(d4);
        mv4 = mMap.addMarker(new MarkerOptions().position(v4).title("Aadesh Mallya").snippet("Distance : " + s4 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv4.setTag(3);

        Location t5 = new Location(LocationManager.GPS_PROVIDER);
        t5.setLongitude(v5.longitude);
        t5.setLatitude(v5.latitude);
        double d5 = round(((startLoc.distanceTo(t5)) / 1000) * 100.0) / 100.0;
        String s5 = String.valueOf(d5);
        mv5 = mMap.addMarker(new MarkerOptions().position(v5).title("Sadhiman Das").snippet("Distance : " + s5 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv5.setTag(4);

        Location t6 = new Location(LocationManager.GPS_PROVIDER);
        t6.setLongitude(v6.longitude);
        t6.setLatitude(v6.latitude);
        double d6 = round(((startLoc.distanceTo(t6)) / 1000) * 100.0) / 100.0;
        String s6 = String.valueOf(d6);
        mv6 = mMap.addMarker(new MarkerOptions().position(v6).title("Samyak Jhaveri").snippet("Distance : " + s6 + " kms").icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_marker_5_45x45)));
        mv6.setTag(5);
// TO GO TO BOOKING PAGE


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Map<String,Object> h = new HashMap<>();
                int id = Integer.parseInt(marker.getTag().toString());
                switch(id) {

                    case 0:

                        h.put("Driver ID", "RS01");
                        h.put("Driver Name","Danny Hiramanek");
                        h.put("Driver number", 997856415);
                        h.put("Vehicle Number","MH 27 C 2169 ");
                        break;
                    case 1:
                        h.put("Driver ID", "RS02");
                        h.put("Driver Name","Tanay Maheshwari");
                        h.put("Driver number", 987856415);
                        h.put("Vehicle Number","MH 42 C 2269 ");
                        break;
                    case 2:
                        h.put("Driver ID", "RS03");
                        h.put("Driver Name","Dhruvin Mehta");
                        h.put("Driver number", 997812415);
                        h.put("Vehicle Number","MH 36 B 2369 ");

                        break;
                    case 3:
                        h.put("Driver ID", "RS04");
                        h.put("Driver Name","Aadesh Mallya");
                        h.put("Driver number", 997851215);
                        h.put("Vehicle Number","MH 04 A 2469 ");

                        break;
                    case 4:
                        h.put("Driver ID", "RS05");
                        h.put("Driver Name","Sadhiman Das");
                        h.put("Driver number", 997880415);
                        h.put("Vehicle Number","MH 20 AE 2569 ");

                        break;
                    case 5:
                        h.put("Driver ID", "RS06");
                        h.put("Driver Name","Samyak Jhaveri");
                        h.put("Driver number", 997850215);
                        h.put("Vehicle Number","MH 47 O 2669 ");

                        break;


                        default:
                            h.put("Invalid Driver","Please select a valid option");
                }
                //String id = databaseReference.push().getKey();
                //Drivers drivers = new Drivers(Dname);
                //databaseReference.child(id).setValue(drivers);
                /*HashMap<String,String> h = new HashMap<>();
                h.put("ddddd",Dname);*/
                String t = Timestamp.now().toDate().toString();

                db.collection("Drivers").document(mAuth.getCurrentUser().getEmail()).set(h).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MapsActivity.this, "Valid Rider not selected", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(MapsActivity.this, Booking_pg.class);
                startActivity(intent);


            }
        });





    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {



    }



}

