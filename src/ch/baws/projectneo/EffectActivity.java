package ch.baws.projectneo;

import java.util.Timer;

import ch.baws.projectneo.R;
import ch.baws.projectneo.effects.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class EffectActivity extends Activity {
	
	TextView title, textview[];
	
	private static final String TAG = "EFFECTS_ACTIVITY";
	private static final boolean D = true;
	
	Timer timer;
	SendTimer snd;
	
	private BluetoothUtils Bluetooth = null;
	
	private EffectDrawer drawer;
	

	private boolean timerisAlive = false; 
	
	public void onCreate(Bundle bndl)
	{
    	super.onCreate(bndl);
    	setContentView(R.layout.effects);
    	
    	title = (TextView)findViewById(R.id.title);
    	
    	textview = new TextView[8];
    	
    	textview[0] = (TextView)findViewById(R.id.textview0);
    	textview[1] = (TextView)findViewById(R.id.textview1);
    	textview[2] = (TextView)findViewById(R.id.textview2);
    	textview[3] = (TextView)findViewById(R.id.textview3);
    	textview[4] = (TextView)findViewById(R.id.textview4);
    	textview[5] = (TextView)findViewById(R.id.textview5);
    	textview[6] = (TextView)findViewById(R.id.textview6);
    	textview[7] = (TextView)findViewById(R.id.textview7);
    	
    	drawer = new EffectDrawer(textview); 
	}
    @Override
    public void onStart() {
    	super.onStart();
    	if (D)
    		Log.e(TAG, "++ ON START ++");
    }
   	@Override
   	public void onResume() {
   		super.onResume();

   		if (D) {
   			Log.e(TAG, "+ ON RESUME +");
   		}
   		
   		
   	}

   	@Override
   	public void onPause() {
   		super.onPause();
   		if (D)
   			Log.e(TAG, "- ON PAUSE -");

   	}

   	@Override
   	public void onStop() {
   		super.onStop();
    	if(timerisAlive==true)
    	{
    		timer.cancel();
    	}
   		if (D)
   			Log.e(TAG, "-- ON STOP --");
   	}

   	@Override
   	public void onDestroy() {
   		super.onDestroy();
    	if(timerisAlive==true)
    	{
    		timer.cancel();
    	}
   		if (D)
   			Log.e(TAG, "--- ON DESTROY ---");
   	}
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.effects_menu, menu);
        return true;
    }
   	
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.wave:
        	
       		if (D) 
       			Log.e(TAG, "+ WAVE BUTTON SELECT +");
        	if(timerisAlive==true)
        	{
        		timer.cancel();
        	}     
        	
        	title.setText("Wave Effect started");
        	
       		Bluetooth = new BluetoothUtils();

        	Bluetooth.init();
        	Bluetooth.connect();
        	
        	Wave wave = new Wave();

        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	timer = new Timer(); 
        	snd = new SendTimer(wave, Bluetooth, drawer);  
        	timer.schedule  ( snd, 100, 33 ); // frequency 30 fps
        	      		       			
        	timerisAlive = true;
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
       		
            return true;

        
        case R.id.starsky:
       		if (D) 
       			Log.e(TAG, "+ STARSKY BUTTON SELECT +");
        	if(timerisAlive==true)
        	{
        		timer.cancel();
        	}       	
        	
        	title.setText("StarSky Effect started");
        	
       		Bluetooth = new BluetoothUtils();

        	Bluetooth.init();
        	Bluetooth.connect();
        	
        	StarSky sky = new StarSky();

        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	timer = new Timer(); 
        	snd = new SendTimer(sky, Bluetooth, drawer);  
        	timer.schedule  ( snd, 1000, 33 ); // frequency 30 fps
        	      		       			
        	timerisAlive = true;
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        	
        case R.id.rsnake:
       		if (D) 
       			Log.e(TAG, "+ RSNAKE BUTTON SELECT +");
        	if(timerisAlive==true)
        	{
        		timer.cancel();
        	}       	
        	
        	title.setText("Wave Effect started");
        	
       		Bluetooth = new BluetoothUtils();
        	Bluetooth.init();
        	Bluetooth.connect();
        	
        	RandomSnakePlayer randomsnake = new RandomSnakePlayer();

        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	timer = new Timer(); 
        	snd = new SendTimer(randomsnake, Bluetooth, drawer);  
        	timer.schedule  ( snd, 100, 33 ); // frequency 30 fps   
        	
        	timerisAlive = true;
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        
        }
        return false;
    }
}
