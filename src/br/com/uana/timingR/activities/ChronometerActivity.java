package br.com.uana.timingR.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChronometerActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(this);
		textView.setText("This is the CHRONOMETER tab");
		setContentView(textView);
	}
}
