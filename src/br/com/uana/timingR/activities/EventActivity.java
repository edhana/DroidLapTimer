package br.com.uana.timingR.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(this);
		textView.setText("This is the EVENT tab: Orientation - " + getRequestedOrientation());
		setContentView(textView);
	}
}
