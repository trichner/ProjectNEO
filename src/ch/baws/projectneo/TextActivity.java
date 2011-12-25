package ch.baws.projectneo;


import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Timer;

import timers.SendTimer;
import ch.baws.projectneo.R;
import ch.baws.projectneo.effects.Buttons;
import ch.baws.projectneo.effects.Colorfield;
import ch.baws.projectneo.effects.Text;
import android.content.Context;

import android.util.Log;

public class TextActivity extends Activity {

	
	private static final String TAG = "Text_ACTIVITY";

	private static final boolean D = false;

	private int[][] colorArray; // array to store the current LED colors

	private SendTimer snd; // Timertask
	private Timer timer;
	private boolean timerisAlive = false; 
	
	public boolean connected = false;

	private BluetoothUtils Bluetooth = null;
	
	Buttons buttoneffect = new Buttons();
	
	Button button;
	EditText et;
	Spinner textcolor, backgroundcolor, speed;
	
	PowerManager pm;
	PowerManager.WakeLock wl;
	
	int icolor, iback, ispeed;
	
	Text text;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        
        
//        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
//        wl.acquire();
        
        colorArray = GeneralUtils.emptyArray(8,8); // fills array with zeros
        
        
        button = (Button) findViewById(R.id.button1);
        et = (EditText) findViewById(R.id.text_input);
        
        Spinner colorspin = (Spinner) findViewById(R.id.textcolor);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorspin.setAdapter(adapter1); 
        colorspin.setOnItemSelectedListener(new ColorSelectedListener());
        
        Spinner backspin = (Spinner) findViewById(R.id.backgroundcolor);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backspin.setAdapter(adapter2); 
        backspin.setOnItemSelectedListener(new BackSelectedListener());
        
        Spinner speedspin = (Spinner) findViewById(R.id.speed);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.speeds, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedspin.setAdapter(adapter3); 
        speedspin.setOnItemSelectedListener(new SpeedSelectedListener());
        
 		Bluetooth = new BluetoothUtils();

    	Bluetooth.init();
    	if (!connected) Bluetooth.connect();
    	connected=true;
    	if(timerisAlive)
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
//   			Log.e(TAG, "+ ABOUT TO ATTEMPT CLIENT CONNECT +");
   		}
   		
   	}

   	@Override
   	public void onPause() {
   		super.onPause();

   		if (D)
   			Log.e(TAG, "- ON PAUSE -");

//   		if (outStream != null) {
//   			try {
//   				outStream.flush();
//   			} catch (IOException e) {
//   				Log.e(TAG, "ON PAUSE: Couldn't flush output stream.", e);
//   			}
//   		}
   		//Bluetooth.Close();


   	}

   	@Override
   	public void onStop() {
   		super.onStop();
    	if(timerisAlive==true)
    	timer.cancel();
    	if(connected)
    		Bluetooth.close();
   		if (D)
   			Log.e(TAG, "-- ON STOP --");
   	}

   	@Override
   	public void onDestroy() {
   		super.onDestroy();
  // 		wl.release();
    	if(timerisAlive==true)
    	timer.cancel();
    	if(connected)
    		Bluetooth.close();
   		if (D)
   			Log.e(TAG, "--- ON DESTROY ---");
   	}

   	public class ColorSelectedListener implements OnItemSelectedListener {
		//@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int pos, long id) {
						icolor = pos;	   	   	        	  
	   	}

		//@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		} 
   	}
   	
   	public class BackSelectedListener implements OnItemSelectedListener {
		//@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int pos, long id) {
						iback = pos;	   	   	        	  
	   	}

		//@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		} 
   	}
 
   	public class SpeedSelectedListener implements OnItemSelectedListener {
		//@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int pos, long id) {
						ispeed = pos;	   	   	        	  
	   	}

		//@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		} 
   	}

   	public void sendText(View v){
   		if (D) 
   			Log.e(TAG, "+ TEXT BUTTON SELECT +");
   		
   		String str = (et.getText()).toString();
    	if(str=="") str="Project Neo"; //TODO
    	//if (!timerisAlive) {
   			text = new Text(str, icolor, iback, ispeed);
   			snd.setEffect(text);
   		//}
    	

    	//String str = "ABC";
    	//text.setText(str, 1, 0, 0);
    	


   	}
    



	
}

    

    
    


