package com.example.carrent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.firebase.geofire.GeoFireUtils;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQueryBounds;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarPickup extends AppCompatActivity {

    Location mCurrentLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup);
        getLocationAndSetData(false);
    }

    @SuppressLint("DefaultLocale")
    private String getDistance(GeoPoint geoPoint){
        Location newLocation = new Location("");
        newLocation.setLatitude(geoPoint.getLatitude());
        newLocation.setLongitude(geoPoint.getLongitude());

        float distance =  mCurrentLocation.distanceTo(newLocation);

        distance /= 1000;

        return String.format("%.1f", distance) + " km away";

    }

    private void setViewData(List<CarRental> carRentals) {
        ListView listView = findViewById(R.id.customListView);
        CarRentalAdapter adapter = new CarRentalAdapter(getApplicationContext(),carRentals);
        listView.setAdapter(adapter);

        //Code
//        TextView txt = (TextView) findViewById(R.id.phone_textview);
//        String number = txt.getText().toString();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel: "+number));
//                startActivity(intent);
//            }
//        });
    }

    private void fillClosestRentals(GeoLocation center) {


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (center == null)
            return;

        final double radiusInM = 10 * 1000;

// Each item in 'bounds' represents a startAtendAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
        List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
        final List<Task<QuerySnapshot>> tasks = new ArrayList<>();
        for (GeoQueryBounds b : bounds) {
            Query q = db.collection("carRentals")
                    .orderBy("geohash")
                    .startAt(b.startHash)
                    .endAt(b.endHash);
            tasks.add(q.get());
        }

// Collect all the query results together into a single list
        Tasks.whenAllComplete(tasks)
                .addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Task<?>>> t) {
                        List<CarRental> closestRentals = new ArrayList<>();

                        for (Task<QuerySnapshot> task : tasks) {
                            QuerySnapshot snap = task.getResult();
                            for (DocumentSnapshot doc : snap.getDocuments()) {
                                GeoPoint location = doc.getGeoPoint("location");
                                double lat = location.getLatitude();
                                double lng = location.getLongitude();

                                // We have to filter out a few false positives due to GeoHash
                                // accuracy, but most will match
                                GeoLocation docLocation = new GeoLocation(lat, lng);
                                double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                                if (distanceInM <= radiusInM) {
                                    addRental(closestRentals, doc);
                                }
                            }
                        }

                        setViewData(closestRentals);

                        if (closestRentals.size() == 0) {
                            Toast.makeText(CarPickup.this, "No cars found within 10km(s) radius", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addRental(List<CarRental> rentals, DocumentSnapshot doc) {
        rentals.add(new CarRental(
                doc.getString("name"),
                doc.getString("phone"),
                doc.getString("description"),
                getDistance(doc.getGeoPoint("location")),
                doc.getString("Car Model")
        ));
    }


    private void getLocationAndSetData(boolean isReattempt) {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            // Get the user's current location
            FusedLocationProviderClient locationClient =
                    LocationServices.getFusedLocationProviderClient(this);
            locationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                mCurrentLocation = location;
                                GeoLocation geoLoc = new GeoLocation(location.getLatitude(), location.getLongitude());
                                fillClosestRentals(geoLoc);
                            }
                        }
                    });

        } else {
            if (isReattempt)
                return;
            // Request location permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            //Reattempt
            getLocationAndSetData(true);
        }

    }
}
