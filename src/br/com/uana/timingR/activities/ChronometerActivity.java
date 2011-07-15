package br.com.uana.timingR.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.uana.timingR.R;
import br.com.uana.timingR.services.LocationService;

public class ChronometerActivity extends Activity {
	private static final String TAG = ChronometerActivity.class.getName();
	private LocationManager locationManager;
	private LocationService locationService;
	private Thread locationThread = null;
	private int sessionCounter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer_view);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationService = new LocationService(locationManager);
		
		final Activity self = this;
		
		// Adicionando os listener de eventos aos botoes
		final Button startButton = (Button) findViewById(R.id.button1);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				launchGpsAcquireThread();
			}
		});
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
		
		if(!locationService.isGpsEnabled()){
			final Button startButton = (Button) findViewById(R.id.button1);
			startButton.setText("Ativar GPS");
			
			startButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {	
					launchGPSOptions();
				}
			});
		}else {
			launchGpsAcquireThread();
		}
	}
	
	
	private void launchGpsAcquireThread(){
		locationThread = new Thread(locationService);
		locationThread.start();
		incrementSessionCounter();
	}
	
	public void incrementSessionCounter(){
		final TextView labelSessionCounter = (TextView) findViewById(R.id.labelSessionCounter);
		sessionCounter += 1;
		labelSessionCounter.setText("" + sessionCounter);
	}
	
	
	
	
}
