package br.com.uana.timingR.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import br.com.uana.timingR.R;

public class ChronometerActivity extends Activity {
	private int lastRequestedOrientation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer_view);
	}


	@Override
	protected void onPause() {
		super.onPause();
//		if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//		}
	}


	@Override
	protected void onResume() {
		super.onResume();
		
//		if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);		
//		}
	}
	
	
}
