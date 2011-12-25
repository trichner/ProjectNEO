package ch.baws.projectneo;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import timers.SendJob;
import timers.SendTimer;

import ch.baws.projectneo.R;
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
	private static final boolean D = false;
	private static final boolean WL = true;
	
	private SendJob sendJob;
	
	private BluetoothUtils Bluetooth = null;
	
	Colorfield cfield;

	private boolean timerisAlive = false; 
	public boolean connected = false;
	
	PowerManager pm;
	PowerManager.WakeLock wl;
	
	public void onCreate(Bundle bndl)
	{
    	super.onCreate(bndl);
    	setContentView(R.layout.effects);
    	/*if(WL){
	    	pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    	PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
	    	wl.acquire();
    	}*/
    	progressBar = findViewItemById(R.id.progressBar);
    	//progressBar.setVisibility(View.INVISIBLE);
    	
    	title = (TextView)findViewById(R.id.title);
    	
   		Bluetooth = new BluetoothUtils();

   		
    	Bluetooth.init();
        if (!Bluetooth.active()) { // request popup if BT isnt activated
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    	if (!connected){
    		Bluetooth.connect();
    		connected = true;
    	}
    	
    	/*
    	if(timerisAlive==true)
    	{
    		timer.cancel();
    	}      */ 
    	//NO FLAGS! UGLY PROGRAMMING!!!
    	
    	Colorfield eff = new Colorfield();
    	eff.setColor(7);
    	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
    	
    	sendJob = new SendJob(Bluetooth); 
    	sendJob.start();
    	timerisAlive = true;
    	
       	

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
   		//if(WL) wl.release();
   		sendJob.stop();
   		Bluetooth.close();
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
        
        switch (item.getItemId()) {
        case R.id.wave:
        	
       		if (D) 
       			Log.e(TAG, "+ WAVE BUTTON SELECT +");
           	
        	title.setText("Wave Effect started");        	
        	
        	Wave wave = new Wave();       	
        	//wave.setEffectActivity(this);
        	sendJob.setEffect(wave);


        	return true;

        
        case R.id.starsky:
       		if (D) 
       			Log.e(TAG, "+ STARSKY BUTTON SELECT +");
    	        	
        	title.setText("StarSky Effect started");
        	        	
        	StarSky sky = new StarSky();
        	//sky.setEffectActivity(this);
        	sendJob.setEffect(sky);
        	return true;
        	
        case R.id.rsnake:
       		if (D) 
       			Log.e(TAG, "+ RSNAKE BUTTON SELECT +");   	
        	
        	title.setText("RandomSnake Effect started");
        	        	
        	RandomSnakePlayer randomsnake = new RandomSnakePlayer();
        	//randomsnake.setEffectActivity(this);
        	sendJob.setEffect(randomsnake);
        	return true;
        	
        
        case R.id.text:
       		if (D) 
       			Log.e(TAG, "+ TEXT BUTTON SELECT +");   	
        	
        	title.setText("Text Effect started");
       
        	final Intent intent2 = new Intent(this,TextActivity.class);           
        	startActivity(intent2);
        	if(timerisAlive==true)
        	sendJob.stop();//timer.cancel();
        	if(connected) Bluetooth.close();
        	connected =false;
        	return true;
        	
        	
        	
        case R.id.matrix:
       		if (D) 
       			Log.e(TAG, "+ MATRIX BUTTON SELECT +");   	
        	
        	title.setText("Matrix Effect started");
        	        	
        	Matrix matrix = new Matrix();
        	//randomsnake.setEffectActivity(this);
        	sendJob.setEffect(matrix);
        	return true;
        
        case R.id.cfield:
       		if (D) 
       			Log.e(TAG, "+ CFIELD BUTTON SELECT +");   	
        	
        	title.setText("Colorfield Effect started");
        	        	
        	cfield = new Colorfield();
        	//randomsnake.setEffectActivity(this);
        	sendJob.setEffect(cfield);
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
        	        	
        	GameOfLife gol = new GameOfLife();
        	//randomsnake.setEffectActivity(this);
        	sendJob.setEffect(gol);
        	return true;
 
        }
        return false;
    }
    
//	public void draw(int[][] array){
//		String str = new String();
//		str = "";
//		int pos=0;
//		int i=0;
//		if (D) Log.e(TAG, "Start drawing");
//		GeneralUtils.drawArray(array,8,8);
//		for(i=0;pos<8;pos++)
//		{
//			for(i=0;i<8;i++){ // first textview
//				switch (array[pos][i]) {
//					case 0:	
//						str+=("O ");
//					case 1:		        	
//						str+=("R ");
//
//					case 2:		        	
//						str+=("G ");
//	
//					case 3:		        	
//						str+=("B ");
//				}
//
//	        	
//	        }
//			textview[pos].setText(str);
//		}
//		
//		
//	}
    



}