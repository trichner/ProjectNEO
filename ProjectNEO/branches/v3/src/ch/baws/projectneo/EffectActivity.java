package ch.baws.projectneo;

import ch.baws.projectneo.R;
import ch.baws.projectneo.effects.*;
import ch.baws.projectneo.sendService.SendService;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EffectActivity extends Activity implements OnClickListener{
	
	TextView title;
	ProgressBar progressBar;
	
	private static final String TAG = "EFFECTS_ACTIVITY";
	private static final boolean D = true;
	private static final boolean WL = false; //doesn't work...
	
	Colorfield cfield; //TODO UGLY

	public boolean connected = false;
	
	private ToggleButton serviceButton;
	
	private ProjectMORPHEUS application;
	
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
    	serviceButton = (ToggleButton) this.findViewById(R.id.toggleButtonService);
    	title = (TextView)findViewById(R.id.title);
    	application = (ProjectMORPHEUS) getApplication();
    	
    	serviceButton.setOnClickListener(this);
    	
    	if (!(new BluetoothUtils()).active()) { // request popup if BT isnt activated
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    	
	}
	
	@Override
	public void onClick(View v) {
		application = (ProjectMORPHEUS) getApplication();
		switch(v.getId()){
		case R.id.toggleButtonService:
			if(!application.isServiceRunning()){ //service running?
				if(D) Log.d(TAG, "Starting Service...");
				startService(new Intent(this, SendService.class));
				serviceButton.setChecked(true);
				Toast.makeText(this, "starting Service...", Toast.LENGTH_SHORT).show();
			}else{ //stop service
				if(D) Log.d(TAG, "Stopping Service...");
				stopService(new Intent(this, SendService.class));
				Toast.makeText(this, "stopping Service...", Toast.LENGTH_SHORT).show();
			}
			break;
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
    		Log.d(TAG, "++ ON START ++");
    }
   	@Override
   	public void onResume() {
   		//application = (ProjectMORPHEUS) getApplication();
   		super.onResume();
   		if (D) {
   			Log.d(TAG, "+ ON RESUME +");
   			if(application==null){ Log.wtf(TAG,"ERROR: Application is NULL");
   			}else{
   				if(application.isServiceRunning()) Log.d(TAG, "Service is running");
   				else Log.d(TAG, "Service is stopped");
   			}
   		}	
    	Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    	//service running?   	
    	serviceButton.setChecked(application.isServiceRunning());
    	

   	}

   	@Override
   	public void onPause() {
   		super.onPause();
   		if (D)
   			Log.d(TAG, "- ON PAUSE -");
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
   			Log.d(TAG, "-- ON STOP --");
   	}

   	@Override
   	public void onDestroy() {
   		super.onDestroy();
   		if(WL){
   			if(wl.isHeld()) wl.release();
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
    	
    	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        Effect effect;
        ProjectMORPHEUS application = (ProjectMORPHEUS) getApplication();
        switch (item.getItemId()) {
        case R.id.wave:
        	
       		if (D) Log.d(TAG, "+ WAVE BUTTON SELECT +");
        	effect = new Wave();     
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;

        
        case R.id.starsky:
       		if (D) 
       			Log.d(TAG, "+ STARSKY BUTTON SELECT +");
    	        	
        	        	        	
        	effect = new StarSky();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        	
        case R.id.rsnake:
       		if (D) 
       			Log.d(TAG, "+ RSNAKE BUTTON SELECT +");   	
        	        	
        	effect = new Nexus();//new RandomSnakePlayer();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        	
        
        case R.id.text:
       		if (D) 
       			Log.d(TAG, "+ TEXT BUTTON SELECT +");   	
        	
        	title.setText("Text Effect started");
       
        	final Intent intent2 = new Intent(this,TextActivity.class);           
        	startActivity(intent2);
        	
        	return true;
        	
        	
        	
        case R.id.matrix:
       		if (D) 
       			Log.d(TAG, "+ MATRIX BUTTON SELECT +");   	
        	  	
        	effect = new Matrix();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
        
        case R.id.cfield:
       		if (D) 
       			Log.d(TAG, "+ CFIELD BUTTON SELECT +");   	
        	
        	title.setText("Colorfield Effect started");
        	        	
        	cfield = new Colorfield();
        	title.setText(cfield.TITLE + " started");
        	application.setEffect(cfield);
        	return true;
        	
        case R.id.cfsub0:
       		if (D) 
       			Log.d(TAG, "+ CFSUB1 BUTTON SELECT +");   	
        	cfield.setColor(0);
           return true;
        case R.id.cfsub1:
       		if (D) 
       			Log.d(TAG, "+ CFSUB2 BUTTON SELECT +");   	
       		cfield.setColor(1);
           	return true;
        case R.id.cfsub2:
       		if (D) 
       			Log.d(TAG, "+ CFSUB3 BUTTON SELECT +");   	
       		cfield.setColor(2);
           	return true;
        case R.id.cfsub3:
       		if (D) 
       			Log.d(TAG, "+ CFSUB3 BUTTON SELECT +");   	
       		cfield.setColor(3);
           	return true;
           	
           	
        case R.id.gameoflife:
       		if (D) 
       			Log.d(TAG, "+ MATRIX BUTTON SELECT +");   	
        	
        	title.setText("Matrix Effect started");
        	        	
        	effect = new GameOfLife();
        	title.setText(effect.TITLE + " started");
        	application.setEffect(effect);
        	return true;
 
        }
        return false;
    }
 

}