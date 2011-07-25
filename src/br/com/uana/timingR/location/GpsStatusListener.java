package br.com.uana.timingR.location;

import android.location.GpsStatus.Listener;
import android.location.GpsStatus;

public class GpsStatusListener implements Listener {
	public static final String TAG = GpsStatusListener.class.getName();
	
	public static String statusDescription; 
	private static int status;


	@Override
	public void onGpsStatusChanged(int event) {
		GpsStatusListener.status = status;
	}
	
	public static String getStatusDescription(){
		//TODO: Fazer o switch de tradução de estado
		return null;
	}

}
