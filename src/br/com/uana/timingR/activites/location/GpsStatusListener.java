package br.com.uana.timingR.activites.location;

import android.location.GpsStatus.Listener;
import android.location.GpsStatus;

public class GpsStatusListener implements Listener {
	public static final String TAG = GpsStatusListener.class.getName();
	private int status;

	@Override
	public void onGpsStatusChanged(int event) {
		
	}
}
