package br.com.uana.timingR.services;

import android.location.LocationManager;
import android.util.Log;


/**
 * 
 * @author Eduardo
 * <p>description</p> This class controls the location data record process.
 * It's not a service yet, but the intention is to make it a local service 
 * for this application.
 * 
 */
public class LocationService implements Runnable {
	private static final String TAG = LocationService.class.getName();

	public static final int STATUS_NO_GPS = 0;
	public static final int STATUS_GPS_ACTIVE = 1;
	public static final int STATUS_ACQUIRING = 2;
	public static final int STATUS_SERVICE_STOPED = 3;
	
	private LocationManager locationManager;
	private boolean gpsEnabled;
	private int status;
	
	public LocationService(LocationManager locationManager){
		this.locationManager = locationManager;
		status = STATUS_NO_GPS;
	}
	
	public boolean isGpsEnabled(){
		if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
	        gpsEnabled = false; //TODO: Throw exception?
		
		gpsEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
		return  gpsEnabled;
	}
	
	public void acquiringGpsData(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(status != STATUS_SERVICE_STOPED){
			switch(status){
				case STATUS_NO_GPS:
					if(isGpsEnabled())
						status = STATUS_GPS_ACTIVE;
					break;
				case STATUS_GPS_ACTIVE:
					break;
				case STATUS_ACQUIRING:
					break;
			}
		}
	}
	
	public void acquiteData(){
		
	}
	
}
