package com.example.dell.ridesolo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.round;

public class Booking_pg extends FragmentActivity implements OnMapReadyCallback {
    public String s1;
    private GoogleMap mMap;
    private FirebaseFirestore db1;


    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;
    private Polyline currentPolyline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_pg);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints=new ArrayList<>();
        db1 = FirebaseFirestore.getInstance();

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
        final LatLng[] latLng1 = new LatLng[0];
        final LatLng[] latLng2 = new LatLng[0];
        final LatLng[] p1 = new LatLng[1];

        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng Mumbai = new LatLng(19.0760, 72.8777);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mumbai, 11));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (listPoints.size()==2)
                {
                    listPoints.clear();
                    mMap.clear();

                }
                listPoints.add(latLng);
                Toast.makeText(Booking_pg.this, "You have selected a point", Toast.LENGTH_SHORT).show();

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                String l1,l2;

                Location m1 = new Location(LocationManager.GPS_PROVIDER);
                Location m2 = new Location(LocationManager.GPS_PROVIDER);

                if (listPoints.size() == 1)
                {
                    //Location t1 = new Location(LocationManager.GPS_PROVIDER);
                    // t1.setLongitude(v1.longitude);
                    //t1.toString().lastIndexOf(String.valueOf(latLng));
                    //t1.setLatitude(v1.latitude);

                    markerOptions
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                            .title("Starting Point")


                    ;

                        m1.setLatitude(latLng.latitude);
                        m1.setLongitude(latLng.longitude);
                        l1=String.valueOf(latLng);

                        m1.toString().trim();
                    Log.i("POINTER1", String.valueOf(m1));
                    Map<String,Object> h = new HashMap<>();
                   h.put("Coordinates of starting point",m1);
                   db1.collection("Location ").document("Starting Loc").set(h);


                }else
                {
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                            .title("Destination")
                            .snippet("Click to GO!")
                    ;
                    //latLng2[0]= String.valueOf(latLng);
                    //latLng2[0] = latLng;
                    m2.setLatitude(latLng.latitude);
                    l2=String.valueOf(latLng);
                    Log.i("POINTER2", String.valueOf(latLng));
                    m2.setLongitude(latLng.longitude);
                    Map<String,Object> h2 = new HashMap<>();
                    h2.put("Coordinates of starting point",m2);
                    db1.collection("Location ").document("Destination Loc").set(h2);

                }


                mMap.addMarker(markerOptions);
                if (listPoints.size()==2)
                {
                    String url = getRequestUrl(listPoints.get(0), listPoints.get(1));
                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(url);

                }

                double d1 = round(((m1.distanceTo(m2)) / 1000) * 100.0) / 100.0;

                s1 = String.valueOf(d1);

                //Toast.makeText(Booking_pg.this, ""+s1, Toast.LENGTH_SHORT).show();
                //Intent o = new Intent(Booking_pg.this, Other.class);

               //startActivity(o);

            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Booking_pg.this, Other.class);
                intent.putExtra("MY_KEY",s1);
                startActivity(intent);
            }
        });
    }

    private String getRequestUrl(LatLng origin, LatLng dest) {


        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=driving" ;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.google.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        Log.i("TAGG",url);

        return url;

    }

    private String requestDirections(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream= null;
        HttpURLConnection httpURLConnection= null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();


            inputStream= httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            StringBuffer stringBuffer= new StringBuffer();
            String line ="";
            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }

            responseString= stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if (inputStream!=null)
            {
                inputStream.close();

            }
            httpURLConnection.disconnect();
        }
        return responseString;

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {

            case LOCATION_REQUEST:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    mMap.setMyLocationEnabled(true);

                }
                break;
        }
    }


    public class TaskRequestDirections extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... strings) {
            String responseString="";
            try{
                responseString= requestDirections(strings[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Taskparser taskparser= new Taskparser();
            taskparser.execute(s);

        }

    }

    public class Taskparser extends AsyncTask<String, Void, List<List<HashMap<String,String>>>> {


        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject= null;
            List<List<HashMap<String,String>>> routes = null;

            try {
                jsonObject= new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;

        }
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {

            ArrayList<LatLng> points ;
            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists)
            {
                points = new ArrayList<>();

                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path)
                {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));


                }
                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(android.R.color.holo_blue_light);
                polylineOptions.geodesic(true);
            }
            if (polylineOptions!= null)
            {
                mMap.addPolyline(polylineOptions);

            }else{

               //Toast.makeText(Booking_pg.this, "Directions not Found!!!", Toast.LENGTH_SHORT).show();
            }




        }


    }



}


