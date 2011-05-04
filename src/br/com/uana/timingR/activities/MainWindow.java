package br.com.uana.timingR.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import br.com.uana.timingR.R;

/**
 * 
 * @author Eduardo Marques
 * @since 28/4/2011
 * @description Entry point for LapTimer Application for android.
 */
public class MainWindow extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources resources = getResources(); // Get Drawables of the resource obj.
        TabHost tabHost = getTabHost(); // Activity tabhost
        TabHost.TabSpec tabSpec; // Reusable tableSpec
        Intent intent;
        
        // Setup and add each tab to tabhost
        
        
    }
}