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
import android.widget.Toast;

public class EffectActivity extends Activity {
	
	private static final String TAG = "EFFECTS_ACTIVITY";
	private static final boolean D = true;
	
	Timer timer;
	SendTimer snd;
	
	private BluetoothUtils Bluetooth = null;
	

	private boolean timerisAlive = false; 
	
	public void onCreate(Bundle bndl)
	{
    	super.onCreate(bndl);
    	setContentView(R.layout.effects);
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
       			Log.e(TAG, "+ SEND BUTTON SELECT +");
       		
       		Bluetooth = new BluetoothUtils();

        	Bluetooth.init();
        	Bluetooth.connect();
        	
        	Wave wave = new Wave();

        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	timer = new Timer(); 
        	snd = new SendTimer(wave);  
        	timer.schedule  ( snd, 100, 33 ); // frequency 30 fps
        	      		       			
        	timerisAlive = true;
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
       		
            return true;

        
        case R.id.debug:

        	return true;
        
        }
        return false;
    }
}
