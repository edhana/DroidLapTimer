package br.com.uana.timingR.location;

import android.location.GpsStatus.Listener;
import android.location.GpsStatus;
import android.widget.TextView;

public class GpsStatusListener implements Listener {
	public static final String TAG = GpsStatusListener.class.getName();
	
	public static String statusDescription; 
	private static int status;
	public TextView statusField;
	
	public GpsStatusListener(TextView statusField){
		this.statusField = statusField;
	}

	@Override
	public void onGpsStatusChanged(int event) {
		GpsStatusListener.status = status;
		statusField.setText(GpsStatusListener.getStatusDescription());
	}
	
	public static String getStatusDescription(){
		//TODO: Fazer o switch de tradução de estado
		String statusDescription = "SEM STATUS";
		
		switch(GpsStatusListener.status){
			case GpsStatus.GPS_EVENT_FIRST_FIX:
				statusDescription = "PRIMEIRO FIX";
				break;
			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				statusDescription = "SATELITE STATUS";
				break;
			case GpsStatus.GPS_EVENT_STARTED:
				statusDescription = "EVENTOS DE GPS INICIADOS";
				break;
			case GpsStatus.GPS_EVENT_STOPPED:
				statusDescription = "EVENTOS DE GPS PARADOS";
				break;
			default:
				break;
		}
		
		return statusDescription;
	}

}
