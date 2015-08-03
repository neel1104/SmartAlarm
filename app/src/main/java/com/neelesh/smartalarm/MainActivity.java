package com.neelesh.smartalarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzi;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

    private Button addGeofence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addGeofence = (Button) findViewById(R.id.main_addGeofence);

        addGeofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GeoFenceActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status == ConnectionResult.SERVICE_MISSING ||
                status == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                status == ConnectionResult.SERVICE_DISABLED) {

            // GooglePlayServicesUtil.getErrorDialog(status, this, GOOGLE_PLAY_SERVICE_UPDATE_CODE).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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