package com.example.asif.speedmeter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.LocationSource;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationSource.OnLocationChangedListener, com.google.android.gms.location.LocationListener {

    private GoogleApiClient client;
    TextView locationView, detailsView;
    Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        locationView = (TextView) findViewById(R.id.location);
        detailsView = (TextView) findViewById(R.id.detailsView);
        if (client == null) {
            client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        Log.i("tag", "onCreate");
        detailsView.setText("onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        locationView.setText("Searching...");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        /*Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.asif.speedmeter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
        Log.i("tag", "onStart");

        detailsView.setText("onStart");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.asif.speedmeter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);*/
        if (client.isConnected()) client.disconnect();
        Log.i("tag", "onStop");
        detailsView.setText("onStop");
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("tag","onConnected");
        detailsView.setText("onConnected");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(client);
        if(lastLocation!=null){
            locationView.setText(String.format("%s, %s", lastLocation.getLatitude(), lastLocation.getLongitude()));
        }
        else{
            Toast.makeText(this,"No Location Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("tag","onConnectionFailed");
    }

    @Override
    public void onConnectionSuspended(int i) {
        client.connect();
        Log.i("tag","onConnectionSuspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        detailsView.setText("onLocationChanged");
        lastLocation = location;
        locationView.setText(String.format("%s, %s", Double.toString(location.getLatitude()), Double.toString(location.getLongitude()) ));
        Log.i("tag","onLocationChanged");
    }
}
