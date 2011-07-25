package br.com.uana.timingR.activities;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.uana.timingR.R;
import br.com.uana.timingR.location.GpsStatusListener;

public class ChronometerActivity extends Activity implements LocationListener {
	private static final String TAG = ChronometerActivity.class.getName();
	private LocationManager locationManager;
	private Thread locationThread = null;
	private int sessionCounter = 0;
	private Vector<Location> locations;
	private boolean lapTimerStarted = false;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer_view);
		
		startButton = (Button) findViewById(R.id.startB);
		
		if(startButton == null){
			buildAlertMessage("Botao e nulo!");
			return;
		}
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		// Set status Listener
		locationManager.addGpsStatusListener(new GpsStatusListener());
		
		if(isGpsEnabled()){
			startButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				}
			});
		}else{
			startButton.setText("Ativar GPS");
			startButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {	
					launchGPSOptions();
				}
			});
		}
		
	}
	
	public boolean isGpsEnabled(){
		return  locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
	}
	
	private void buildAlertMessageNoGps() {
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Seu GPS parece n‹o estar habilitado. Devo habilit‡-lo?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                   launchGPSOptions(); 
	               }
	           })
	           .setNegativeButton("No", new DialogInterface.OnClickListener() {
	               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                    dialog.cancel();
	               }
	           });
	    final AlertDialog alert = builder.create();
	    alert.show();
	}
	
	private void buildAlertMessage(String message) {
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage(message)
	           .setCancelable(false)
	           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                   dialog.cancel(); 
	               }
	           });
	           
	    final AlertDialog alert = builder.create();
	    alert.show();
	}	
	
	private void launchGPSOptions() {
        final ComponentName toLaunch = new ComponentName("com.android.settings","com.android.settings.SecuritySettings");
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(toLaunch);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 0);
    } 


	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if(isGpsEnabled()){
			startButton.setText(R.string.start);
			startButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					startLapTimer();
				}
			});
		}else{
			startButton.setText("Ativar GPS");
			startButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {	
					launchGPSOptions();
				}
			});
		}		
	}
	
	public void incrementSessionCounter(){
		final TextView labelSessionCounter = (TextView) findViewById(R.id.labelSessionCounter);
		sessionCounter += 1;
		labelSessionCounter.setText("" + sessionCounter);
	}	
	
	@Override
	public void onLocationChanged(Location location) {
		if(lapTimerStarted){
			Log.i(TAG, "=======> Location Recebida: " + location.getLatitude() + ", " + location.getLongitude());
			locations.add(location);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	
	public int getLocationsQuantity(){
		return locations.size();
	}
	
	private void startLapTimer(){
		startButton.setText(R.string.getting_points);
		startButton.setEnabled(false);
		incrementSessionCounter();
		lapTimerStarted = true;
	}
}
