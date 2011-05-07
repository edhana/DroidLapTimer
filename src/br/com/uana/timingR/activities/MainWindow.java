package br.com.uana.timingR.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
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
        
        // Eliminates color banding
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);        
        
        setContentView(R.layout.main);
        
        Resources resources = getResources(); // Get Drawables of the resource obj.
        TabHost tabHost = getTabHost(); // Activity tabhost
        TabHost.TabSpec tabSpec; // Reusable tableSpec
        Intent intent; // Reusable intent for each tab
        
        // Setup and add each tab to tabhost ( initializin a tab spec )
        // Chronometer
        intent = new Intent().setClass(this, ChronometerActivity.class);
        tabSpec = tabHost.newTabSpec("cronômetro").setIndicator("Cronômetro",
        		resources.getDrawable(R.drawable.ic_tab_chronometer)).setContent(intent);
        tabHost.addTab(tabSpec);
        
        // Session
        intent = new Intent().setClass(this, SessionActivity.class);
        tabSpec = tabHost.newTabSpec("sessão").setIndicator("Sessão", 
        		resources.getDrawable(R.drawable.ic_tab_session)).setContent(intent);
        tabHost.addTab(tabSpec);
        
        // Event
        intent = new Intent().setClass(this, EventActivity.class);
        tabSpec = tabHost.newTabSpec("evento").setIndicator("Evento", 
        		resources.getDrawable(R.drawable.ic_tab_event)).setContent(intent);
        tabHost.addTab(tabSpec);        
        
        //Setup
        intent = new Intent().setClass(this, SetupActivity.class);
        tabSpec = tabHost.newTabSpec("configuração.").setIndicator("Configuração", 
        		resources.getDrawable(R.drawable.ic_tab_setup)).setContent(intent);
        tabHost.addTab(tabSpec);           
        
        tabHost.setCurrentTab(2);
    }
}