package ch.baws.projectneo;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import ch.baws.projectneo.R;
import ch.baws.projectneo.bthandler.SendJob;
import ch.baws.projectneo.bthandler.SendTimer;
import ch.baws.projectneo.effects.*;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class EffectActivity extends Activity {
	
	TextView title;
	ProgressBar progressBar;
	
	private static final String TAG = "EFFECTS_ACTIVITY";
	private static final boolean D = true;
	private static final boolean WL = false; //doesn't work...
	
	private SendJob sendJob;
	
	private BluetoothUtils bluetooth = null;
	
	Colorfield cfield; //TODO UGLY

	private boolean timerisAlive = false; 
	public boolean connected = false;
	
	PowerManager pm;
	PowerManager.WakeLock wl;
	
	public void onCreate(Bundle bndl)
	{
    	super.onCreate(bndl);
    	setContentView(R.layout.effects);
    	if(WL){
	    	pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    	PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "ProjectNeo");
	    	if(!wl.isHeld()) wl.acquire();
    	}
    	progressBar = findViewItemById(R.id.progressBar);
    	//progressBar.setVisibility(View.INVISIBLE);
    	
    	title = (TextView)findViewById(R.id.title);
    	
    	if (!(new BluetoothUtils()).active()) { // request popup if BT isnt activated
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    	
	}
    private ProgressBar findViewItemById(int progressbar2) {
		// TODO Auto-generated method stub
		return null;
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
   		/*
        if (!connected){
        	Bluetooth.connect();
    		connected = true;
    	}*/
    	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
    	
    	//sendJob = new SendJob(Bluetooth); 
    	sendJob.start();
    	//timerisAlive = true;

   		if (D) {
   			Log.e(TAG, "+ ON RESUME +");
   		}
   		
   		
   	}

   	@Override
   	public void onPause() {
   		super.onPause();
   		if (D)
   			Log.e(TAG, "- ON PAUSE -");
   		sendJob.stop();
   	}

   	@Override
   	public void onStop() {
   		super.onStop();
    	/*
   		if(timerisAlive==true)
    	{
    		timer.cancel();
    	}
    	Bluetooth.close();*/
   		if (D)
   			Log.e(TAG, "-- ON STOP --");
   	}

   	@Override
   	public void onDestroy() {
   		super.onDestroy();
   		if(WL){
   			if(wl.isHeld()) wl.release();
   		}

   		/*if(timerisAlive==true)
    	{
    		timer.cancel();
    	}*/
    	
   		if (D)
   			Log.e(TAG, "--- ON DESTROY ---");
   	}
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.effects_menu, menu);
        return true;
    }
   	
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        Effect effect;
        ProjectMORPHEUS application = (ProjectMORPHEUS) getApplication();
        switch (item.getItemId()) {
        case R.id.wave:
        	
       		if (D) 
       			Log.e(TAG, "+ WAVE BUTTON SELECT +");
           	
        	        	
        	
        	effect = new Wave();     
        	title.setText(effect.TITLE + " started");
        	//wave.setEffectActivity(this);
        	application.setEffect(effect);


        	return true;

        
        case R.id.starsky:
       		if (D) 
       			Log.e(TAG, "+ STARSKY BUTTON SELECT +");
    	        	
        	        	        	
        	effect = new StarSky();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        	
        case R.id.rsnake:
       		if (D) 
       			Log.e(TAG, "+ RSNAKE BUTTON SELECT +");   	
        	        	
        	effect = new Nexus();//new RandomSnakePlayer();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        	
        
        case R.id.text:
       		if (D) 
       			Log.e(TAG, "+ TEXT BUTTON SELECT +");   	
        	
        	title.setText("Text Effect started");
       
        	final Intent intent2 = new Intent(this,TextActivity.class);           
        	startActivity(intent2);
        	
        	return true;
        	
        	
        	
        case R.id.matrix:
       		if (D) 
       			Log.e(TAG, "+ MATRIX BUTTON SELECT +");   	
        	  	
        	effect = new Matrix();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        
        case R.id.cfield:
       		if (D) 
       			Log.e(TAG, "+ CFIELD BUTTON SELECT +");   	
        	
        	title.setText("Colorfield Effect started");
        	        	
        	cfield = new Colorfield();
        	title.setText(cfield.TITLE + " started");
        	application.setEffect(cfield);
        	return true;
        	
        case R.id.cfsub0:
       		if (D) 
       			Log.e(TAG, "+ CFSUB1 BUTTON SELECT +");   	
        	cfield.setColor(0);
           return true;
        case R.id.cfsub1:
       		if (D) 
       			Log.e(TAG, "+ CFSUB2 BUTTON SELECT +");   	
       		cfield.setColor(1);
           	return true;
        case R.id.cfsub2:
       		if (D) 
       			Log.e(TAG, "+ CFSUB3 BUTTON SELECT +");   	
       		cfield.setColor(2);
           	return true;
        case R.id.cfsub3:
       		if (D) 
       			Log.e(TAG, "+ CFSUB3 BUTTON SELECT +");   	
       		cfield.setColor(3);
           	return true;
           	
           	
        case R.id.gameoflife:
       		if (D) 
       			Log.e(TAG, "+ MATRIX BUTTON SELECT +");   	
        	
        	title.setText("Matrix Effect started");
        	        	
        	effect = new GameOfLife();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
 
        }
        return false;
    }
 

}