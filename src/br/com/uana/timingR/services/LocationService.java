package br.com.uana.timingR.services;

import java.util.Vector;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


/**
 * 
 * @author Eduardo
 * <p>description</p> This class controls the location data record process.
 * It's not a service yet, but the intention is to make it a local service 
 * for this application.
 * 
 */
public class LocationService implements Runnable, LocationListener {
	private static final String TAG = LocationService.class.getName();

	public static final int STATE_NO_GPS = 0;
	public static final int STATE_GPS_ACTIVE = 1;
	public static final int STATE_ACQUIRING = 2;
	public static final int STATE_SERVICE_PAUSED = 3;
	public static final int STATE_SERVICE_STOPED = 4;
	
	private LocationManager locationManager;
	private boolean gpsEnabled;
	private int state;
	
	private Vector<Location> locations;
	
	public LocationService(LocationManager locationManager){
		this.locationManager = locationManager;
		state = STATE_NO_GPS;
		locations = new Vector<Location>();
	}
	
	public boolean isGpsEnabled(){
		if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
	        gpsEnabled = false; //TODO: Throw exception?
		
		gpsEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
		return  gpsEnabled;
	}
	
	public void acquireGpsData(){
		Log.d(TAG, "===> Aquire GPS Data -- Still here");
	}
	
	@Override
	public void run() {
		while(state != STATE_SERVICE_STOPED){
			try {
				switch(state){
					case LocationService.STATE_NO_GPS:
//						startButton.setEnabled(false); startButton e nulo aqui
						if(isGpsEnabled())
							state = LocationService.STATE_GPS_ACTIVE;
						else
							state = LocationService.STATE_NO_GPS;
						break;
					case LocationService.STATE_GPS_ACTIVE:
						Log.d(TAG, "===> GPS Active");
						state = STATE_GPS_ACTIVE;
						break;
					case LocationService.STATE_ACQUIRING:
						acquireGpsData();
						break;
					case LocationService.STATE_SERVICE_PAUSED:
						Log.d(TAG, "GPS PAUSED");
						state = LocationService.STATE_SERVICE_PAUSED;
						break;
					default:
						state = LocationService.STATE_NO_GPS;
						break;
				}
				
				Thread.sleep(100);
			} catch ( InterruptedException e) {
				Log.e(TAG, e.getMessage());
			}
		}
	}
	
	/**
	 * TODO: Fazer desse o metodo que recebe os pontos do servidor e grava em 
	 * algum tipo de modelo de posições
	 */
	public boolean start(){
		if(isGpsEnabled()){
			state = LocationService.STATE_ACQUIRING;
			return true;
		}else {
			state = LocationService.STATE_NO_GPS;
		}
		
		return false;
	}
	
	public void stop(){
		state = LocationService.STATE_SERVICE_PAUSED;
	}
	
	public int getState(){
		return state;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		//TODO: Gravar os locations na memoria do celular
		locations.add(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
	
	public int getLocationsQuantity(){
		return locations.size();
	}
}
