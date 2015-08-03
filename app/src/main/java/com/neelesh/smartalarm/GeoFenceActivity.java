package com.neelesh.smartalarm;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class GeoFenceActivity extends ActionBarActivity  implements ConnectionCallbacks,OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private TextView mLatituteText;
    private TextView mLongitudeText;
    private LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fence);

        Log.i("onCreate","!");
        buildGoogleApiClient();

        mLatituteText = (TextView) findViewById(R.id.geo_latitudeText);
        mLongitudeText = (TextView) findViewById(R.id.geo_longitudeText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected synchronized void buildGoogleApiClient(){
        Log.i("buildApiClient","!");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
        .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
        .addApi(LocationServices.API)
        .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(1000);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("onConncected","!");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLastLocation != null) {
            mLatituteText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("onConncectionSusp","!");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("onConncectionFail",connectionResult.toString());
        Log.e("onConncectionFail", "" + connectionResult.getErrorCode());

        mLatituteText.setText("error");
        mLongitudeText.setText("error");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geo_fence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
