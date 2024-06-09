package com.hieult.foodhub.activity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hieult.foodhub.R;
import ai.nextbillion.kits.directions.models.DirectionsResponse;
import ai.nextbillion.kits.directions.models.DirectionsRoute;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import ai.nextbillion.kits.geojson.Point;
import ai.nextbillion.maps.Nextbillion;
import ai.nextbillion.maps.camera.CameraUpdate;
import ai.nextbillion.maps.camera.CameraUpdateFactory;
import ai.nextbillion.maps.core.MapView;
import ai.nextbillion.maps.core.NextbillionMap;
import ai.nextbillion.maps.core.OnMapReadyCallback;
import ai.nextbillion.maps.location.engine.LocationEngine;
import ai.nextbillion.maps.location.engine.LocationEngineCallback;
import ai.nextbillion.maps.location.engine.LocationEngineProvider;
import ai.nextbillion.maps.location.engine.LocationEngineRequest;
import ai.nextbillion.maps.location.engine.LocationEngineResult;
import ai.nextbillion.maps.location.modes.RenderMode;
import ai.nextbillion.navigation.ui.NBNavigation;

import ai.nextbillion.navigation.ui.camera.CameraUpdateMode;
import ai.nextbillion.navigation.ui.camera.NavigationCameraUpdate;
import ai.nextbillion.navigation.ui.map.NavNextbillionMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapDeliveryActivity extends AppCompatActivity {


    final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 500;
    private static final int DEFAULT_CAMERA_ZOOM = 11;
    private static final int CAMERA_ANIMATION_DURATION = 1000;
    LocationEngine locationEngine;
    private static NavNextbillionMap navNextbillionMap;
    private static boolean locationFound;
    ai.nextbillion.kits.geojson.Point currentLocation;
    MapView mapView;
    DirectionsRoute route;
    ai.nextbillion.kits.geojson.Point origin, destination;
    DatabaseReference databaseReference;

    final NavigationLauncherLocationCallback callbackL =
            new NavigationLauncherLocationCallback(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Nextbillion.getInstance(getApplicationContext(), "your-nextbillion-api-key-here");
        setContentView(R.layout.activity_map_delivery);
        origin = ai.nextbillion.kits.geojson.Point.fromLngLat(40.742352, -74.006210);
        mapView = findViewById(R.id.mapView);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("addAddress").child("Home Address");
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull NextbillionMap nextbillionMap) {
                String styleUri = "https://api.nextbillion.io/maps/streets/style.json?key="
                        + Nextbillion.getAccessKey();
                nextbillionMap.setStyle(new ai.nextbillion.maps.core.Style.Builder().fromUri(styleUri));
                nextbillionMap.getStyle(new ai.nextbillion.maps.core.Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull ai.nextbillion.maps.core.Style style) {
                        navNextbillionMap = new NavNextbillionMap(mapView, nextbillionMap);
                        navNextbillionMap.updateLocationLayerRenderMode(RenderMode.COMPASS);
                        initializeLocationEngine();
                        animateCamera(new ai.nextbillion.maps.geometry.LatLng(origin.latitude(), origin.longitude()));
                        navNextbillionMap.addMarker(getApplicationContext(), destination);
                        fetchRoute();
                    }
                });
            }
        });
    }
    public void fetchRoute(){
        fetchDestinationFromFirebase();

        NBNavigation.fetchRoute(origin, destination, new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call,
                                   Response<DirectionsResponse> response) {
                if (response.isSuccessful()){
                    DirectionsResponse directionsResponse = response.body();
                    DirectionsRoute route = directionsResponse.routes().get(0);
                    if (route.distance() > 25d){
                        MapDeliveryActivity.this.route = route;
                        navNextbillionMap.drawRoute(route);
                    }
                    else
                        Snackbar.make(mapView, "Select longer route", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {

            }
        });
    }
    private void fetchDestinationFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String streets = dataSnapshot.child("street").getValue(String.class);
                    String city = dataSnapshot.child("city").getValue(String.class);
                    String address = streets + city;
                    Log.d("MyData", "showAddress: " + address);
                    Log.d("MyData", "showStreet: " + streets);
                    Log.d("MyData", "showCity: " + city);
                    addFirebaseDestinationMarker(address);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MapDeliveryActivity.this, "Error fetching data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addFirebaseDestinationMarker(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            if (!addresses.isEmpty()) {
                double destinationLatitude = addresses.get(0).getLatitude();
                double destinationLongitude = addresses.get(0).getLongitude();
                LatLng destinationLatLng = new LatLng(destinationLatitude, destinationLongitude);
                Log.d("MyData", "showAddress: " + destinationLatLng);
                destination = ai.nextbillion.kits.geojson.Point.fromLngLat(destinationLongitude, destinationLatitude);
                // Hiển thị đích đến trong Log để kiểm tra
                Log.d("MyData", "Destination Point: " + destination.toJson());
            } else {
                Toast.makeText(MapDeliveryActivity.this, "Error converting address to coordinates", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MapDeliveryActivity.this, "Error converting address to coordinates", Toast.LENGTH_SHORT).show();
        }
    }
    @NonNull
    private LocationEngineRequest buildEngineRequest() {
        return new LocationEngineRequest.Builder(UPDATE_INTERVAL_IN_MILLISECONDS).
                setFastestInterval(UPDATE_INTERVAL_IN_MILLISECONDS).
                setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY).build();
    }

    @SuppressWarnings({"MissingPermission"})
    private void initializeLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);
        LocationEngineRequest request = buildEngineRequest();
        locationEngine.requestLocationUpdates(request, callbackL, null);
        locationEngine.getLastLocation(callbackL);
    }
    private static class NavigationLauncherLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<MapDeliveryActivity> activityWeakReference;

        NavigationLauncherLocationCallback(MapDeliveryActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(LocationEngineResult result) {
            MapDeliveryActivity activity = activityWeakReference.get();
            if (activity != null) {
                Location location = result.getLastLocation();
                if (location == null) {
                    return;
                }
                activity.updateCurrentLocation(Point.fromLngLat(location.getLongitude(), location.getLatitude()));
                activity.onLocationFound(location);
            }
        }

        @Override
        public void onFailure(@NonNull Exception exception) {
            exception.printStackTrace();
        }
    }

    void updateCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    void onLocationFound(Location location) {
        navNextbillionMap.updateLocation(location);
        if (!locationFound) {
            animateCamera(new ai.nextbillion.maps.geometry.LatLng(location.getLatitude(), location.getLongitude()));
            locationFound = true;
        }
    }

    private static void animateCamera(ai.nextbillion.maps.geometry.LatLng point) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(point, DEFAULT_CAMERA_ZOOM);
        NavigationCameraUpdate navigationCameraUpdate = new NavigationCameraUpdate(cameraUpdate);
        navigationCameraUpdate.setMode(CameraUpdateMode.OVERRIDE);
        navNextbillionMap.retrieveCamera().update(navigationCameraUpdate, CAMERA_ANIMATION_DURATION);
    }
}

