package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Minimum SDK level 26
 */

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 999;
    private boolean isConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFragment();
    }

    private void loadFragment() {
    getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,new MapFragment()).commit();
    }
    private void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_CODE);
        }
    }
    /*BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isConnected = intent.getBooleanExtra("connected",false);
        }
    };

     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission granted", Toast.LENGTH_SHORT).show();
                }else{
                    checkPermissions();
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
       // LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("Network-stage-check"));
    }

    @Override
    protected void onPause() {
        super.onPause();
       // LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}