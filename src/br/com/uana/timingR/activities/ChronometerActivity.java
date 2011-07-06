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
import android.widget.Button;
import br.com.uana.timingR.R;

public class ChronometerActivity extends Activity {
	private LocationManager manager;
	private boolean isGpsEnabled= false;
	
	private void verifyGps(){
		if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
	        buildAlertMessageNoGps();
		
		isGpsEnabled = manager.isProviderEnabled( LocationManager.GPS_PROVIDER );
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer_view);
		
		manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
		if(!isGpsEnabled){
			final Button startButton = (Button) findViewById(R.id.button1);
			startButton.setText("Procurando GPS...");
		}
		
		verifyGps();
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
		
//		if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
//	        buildAlertMessageNoGps();
//	    }
		
		if(!isGpsEnabled){
			final Button startButton = (Button) findViewById(R.id.button1);
			startButton.setText("Procurando GPS...");
		}
		
		verifyGps();		
	}
	
	
	
	
}
