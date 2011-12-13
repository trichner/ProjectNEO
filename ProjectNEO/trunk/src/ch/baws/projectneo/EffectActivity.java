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
	
	TextView title;

	private TextView textview[];
	
	private static final String TAG = "EFFECTS_ACTIVITY";
	private static final boolean D = false;
	
	Timer timer;
	SendTimer snd;
	
	private BluetoothUtils Bluetooth = null;
	
	Colorfield cfield;

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
    	
   		Bluetooth = new BluetoothUtils();

    	Bluetooth.init();
    	Bluetooth.connect();
    	if(timerisAlive==true)
    	{
    		timer.cancel();
    	}       
    	
    	Colorfield eff = new Colorfield();
    	eff.setColor(7);
    	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
    	timer = new Timer(); 
    	snd = new SendTimer(eff, Bluetooth);  
    	timer.schedule  ( snd, 100, 66 ); // frequency 15 fps
    	      		       			
    	timerisAlive = true;
       	

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
    	Bluetooth.close();
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
    	Bluetooth.close();
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
           	
        	title.setText("Wave Effect started");        	
        	
        	Wave wave = new Wave();       	
        	//wave.setEffectActivity(this);
        	snd.setEffect(wave);

        	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
       		
            return true;

        
        case R.id.starsky:
       		if (D) 
       			Log.e(TAG, "+ STARSKY BUTTON SELECT +");
    	        	
        	title.setText("StarSky Effect started");
        	        	
        	StarSky sky = new StarSky();
        	//sky.setEffectActivity(this);
        	snd.setEffect(sky);
        	
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        	
        case R.id.rsnake:
       		if (D) 
       			Log.e(TAG, "+ RSNAKE BUTTON SELECT +");   	
        	
        	title.setText("RandomSnake Effect started");
        	        	
        	RandomSnakePlayer randomsnake = new RandomSnakePlayer();
        	//randomsnake.setEffectActivity(this);
        	snd.setEffect(randomsnake);
        	
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        	
        
        case R.id.text:
       		if (D) 
       			Log.e(TAG, "+ TEXT BUTTON SELECT +");   	
        	
        	title.setText("Text Effect started");
        	        	
        	Text text = new Text();
        	//randomsnake.setEffectActivity(this);
        	snd.setEffect(text);
        	
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        	
        	
        case R.id.matrix:
       		if (D) 
       			Log.e(TAG, "+ MATRIX BUTTON SELECT +");   	
        	
        	title.setText("Matrix Effect started");
        	        	
//        	Matrix matrix = new Matrix();
//        	//randomsnake.setEffectActivity(this);
//        	snd.setEffect(matrix);
        	
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
        	return true;
        
        case R.id.cfield:
       		if (D) 
       			Log.e(TAG, "+ CFIELD BUTTON SELECT +");   	
        	
        	title.setText("Colorfield Effect started");
        	        	
        	cfield = new Colorfield();
        	//randomsnake.setEffectActivity(this);
        	snd.setEffect(cfield);
        	
           	Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
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
        }
        return false;
    }
    
	public void draw(int[][] array){
		String str = new String();
		str = "";
		int pos=0;
		int i=0;
		if (D) Log.e(TAG, "Start drawing");
		GeneralUtils.drawArray(array,8,8);
		for(i=0;pos<8;pos++)
		{
			for(i=0;i<8;i++){ // first textview
				switch (array[pos][i]) {
					case 0:	
						str+=("O ");
					case 1:		        	
						str+=("R ");

					case 2:		        	
						str+=("G ");
	
					case 3:		        	
						str+=("B ");
				}

	        	
	        }
			textview[pos].setText(str);
		}
		
		
	}
    



}