package ch.baws.projectneo;


import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Timer;
import ch.baws.projectneo.R;
import ch.baws.projectneo.effects.Buttons;
import ch.baws.projectneo.effects.Colorfield;
import ch.baws.projectneo.effects.Text;
import android.content.Context;

import android.util.Log;

public class TextActivity extends Activity {

	
	private static final String TAG = "Text_ACTIVITY";

	private static final boolean D = true;

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
	
	Text text;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        
        
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        wl.acquire();
        
        colorArray = GeneralUtils.emptyArray(8,8); // fills array with zeros
        
        
        button = (Button) findViewById(R.id.button1);
        et = (EditText) findViewById(R.id.text_input);
        
        Spinner colorspin = (Spinner) findViewById(R.id.textcolor);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorspin.setAdapter(adapter1); 
        
        Spinner backspin = (Spinner) findViewById(R.id.backgroundcolor);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backspin.setAdapter(adapter2); 
        
        Spinner speedspin = (Spinner) findViewById(R.id.speed);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
                this, R.array.speeds, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedspin.setAdapter(adapter3); 
        
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
   		wl.release();
    	if(timerisAlive==true)
    	timer.cancel();
    	if(connected)
    		Bluetooth.close();
   		if (D)
   			Log.e(TAG, "--- ON DESTROY ---");
   	}

       	  

   	public void sendText(View v){
   		if (D) 
   			Log.e(TAG, "+ TEXT BUTTON SELECT +"); 
   		
    	if (!timerisAlive) {
    		text = new Text();
    		snd.setEffect(text);
    	}
    	//String str = (et.getText()).toString();
    	//if(str=="") str="ABC"; //TODO
    	String str = "ABC";
    	text.setText(str, 1, 0, 0);
    	


   	}
    



	
}

    

    
    


