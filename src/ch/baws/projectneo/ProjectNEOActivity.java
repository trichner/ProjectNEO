package ch.baws.projectneo;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;


import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class ProjectNEOActivity extends Activity {

	
	private static final String TAG = "PN_ACTIVITY";

	public static final String SEND_KEY = "snd";
	private static final boolean D = true;
	public int[][] colorArray; // array to store the current LED colors

	public SendTimer snd; // Timertask
	public Timer timer;
	
	public boolean connected = false;

	private OutputStream outStream = null;

	private BluetoothUtils Bluetooth = null;
	
	Button button00, button01, button02, button03, button04, button05, button06, button07;
	Button button10, button11, button12, button13, button14, button15, button16, button17; // all buttons
	Button button20, button21, button22, button23, button24, button25, button26, button27;
	Button button30, button31, button32, button33, button34, button35, button36, button37;
	Button button40, button41, button42, button43, button44, button45, button46, button47;
	Button button50, button51, button52, button53, button54, button55, button56, button57;
	Button button60, button61, button62, button63, button64, button65, button66, button67;
	Button button70, button71, button72, button73, button74, button75, button76, button77;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        colorArray = GeneralUtils.emptyArray(8,8); // fills array with zeros
        
        button00 = (Button) findViewById(R.id.button00);
        button01 = (Button) findViewById(R.id.button01); // makes sure we can use buttonxx like a variable
        button02 = (Button) findViewById(R.id.button02);
        button03 = (Button) findViewById(R.id.button03);
        button04 = (Button) findViewById(R.id.button04);
        button05 = (Button) findViewById(R.id.button05);
        button06 = (Button) findViewById(R.id.button06);
        button07 = (Button) findViewById(R.id.button07);
 
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11); 
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        
        button20 = (Button) findViewById(R.id.button20);     
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);
        button25 = (Button) findViewById(R.id.button25);
        button26 = (Button) findViewById(R.id.button26);
        button27 = (Button) findViewById(R.id.button27);
        
        button30 = (Button) findViewById(R.id.button30);
        button31 = (Button) findViewById(R.id.button31);
        button32 = (Button) findViewById(R.id.button32);
        button33 = (Button) findViewById(R.id.button33);
        button34 = (Button) findViewById(R.id.button34);
        button35 = (Button) findViewById(R.id.button35);
        button36 = (Button) findViewById(R.id.button36);
        button37 = (Button) findViewById(R.id.button37);
        
        button40 = (Button) findViewById(R.id.button40);
        button41 = (Button) findViewById(R.id.button41);
        button42 = (Button) findViewById(R.id.button42);
        button43 = (Button) findViewById(R.id.button43);
        button44 = (Button) findViewById(R.id.button44);
        button45 = (Button) findViewById(R.id.button45);
        button46 = (Button) findViewById(R.id.button46);
        button47 = (Button) findViewById(R.id.button47);
        
        button50 = (Button) findViewById(R.id.button50);
        button51 = (Button) findViewById(R.id.button51);
        button52 = (Button) findViewById(R.id.button52);
        button53 = (Button) findViewById(R.id.button53);
        button54 = (Button) findViewById(R.id.button54);
        button55 = (Button) findViewById(R.id.button55);
        button56 = (Button) findViewById(R.id.button56);
        button57 = (Button) findViewById(R.id.button57);
        
        button60 = (Button) findViewById(R.id.button60);
        button61 = (Button) findViewById(R.id.button61);
        button62 = (Button) findViewById(R.id.button62);
        button63 = (Button) findViewById(R.id.button63);
        button64 = (Button) findViewById(R.id.button64);
        button65 = (Button) findViewById(R.id.button65);
        button66 = (Button) findViewById(R.id.button66);
        button67 = (Button) findViewById(R.id.button67);
        
        button70 = (Button) findViewById(R.id.button70);
        button71 = (Button) findViewById(R.id.button71);
        button72 = (Button) findViewById(R.id.button72);
        button73 = (Button) findViewById(R.id.button73);
        button74 = (Button) findViewById(R.id.button74);
        button75 = (Button) findViewById(R.id.button75);
        button76 = (Button) findViewById(R.id.button76);
        button77 = (Button) findViewById(R.id.button77);
        
        
        Bluetooth = new BluetoothUtils();

        if (D)
        	Log.e(TAG, "+++ ON CREATE +++");
        
    	if (Bluetooth.init()==false) { //no BT adapter available
    		Toast.makeText(this, 
    			"You need Bluetooth in order to use this program", 
    			Toast.LENGTH_LONG).show();
    		finish();
    		return;
    	}
    	
      if (!Bluetooth.active()) { // request popup if BT isnt activated
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivityForResult(enableBtIntent, 1);
      }

    if (D)
    		Log.e(TAG, "+++ DONE IN ON CREATE, GOT LOCAL BT ADAPTER +++");
    
    

    
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

   		if (outStream != null) {
   			try {
   				outStream.flush();
   			} catch (IOException e) {
   				Log.e(TAG, "ON PAUSE: Couldn't flush output stream.", e);
   			}
   		}
   		//Bluetooth.Close();


   	}

   	@Override
   	public void onStop() {
   		super.onStop();
   		if (D)
   			Log.e(TAG, "-- ON STOP --");
   	}

   	@Override
   	public void onDestroy() {
   		super.onDestroy();
   		if (D)
   			Log.e(TAG, "--- ON DESTROY ---");
   	}

       	  

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.connect:
        	
       		if (D) {
       			Log.e(TAG, "+ SEND BUTTON SELECT +");
       			Log.e(TAG, "+ ABOUT TO ATTEMPT CLIENT CONNECT +");
       			
       		}
        	Bluetooth.connect();
        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	connected = true; //TODO
      		
       		timer = new Timer(); // daemon to send current colorcode
       		snd = new SendTimer();
       		snd.setBluetooth(Bluetooth);
       		snd.setArray(colorArray);
       		timer.schedule  ( snd, 1000, 33 ); // frequency 30 fps
       		
       		Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
       		
       		
            return true;

        
        case R.id.effects:
        	//Toast.makeText(getApplicationContext(), "Nothing to see here", Toast.LENGTH_SHORT).show();
            final Intent intent = new Intent(this,EffectActivity.class);           
        	startActivity(intent);
        	timer.cancel();
        	return true;
        
        }
        return false;
    }
/**
 * method toggleColor  
 * @param v
 */
public void toggleColor(View v){
	
	if (button00.getId() == ((Button)v).getId())
		toggle(button00, colorArray, 0, 0);
	else if (button01.getId() == ((Button)v).getId())
		toggle(button01, colorArray, 0, 1);
	else if (button02.getId() == ((Button)v).getId())
		toggle(button02, colorArray, 0, 2);
	else if (button03.getId() == ((Button)v).getId())
		toggle(button03, colorArray, 0, 3);
	else if (button04.getId() == ((Button)v).getId())
		toggle(button04, colorArray, 0, 4);
	else if (button05.getId() == ((Button)v).getId())
		toggle(button05, colorArray, 0, 5);
	else if (button06.getId() == ((Button)v).getId())
		toggle(button06, colorArray, 0, 6);
	else if (button07.getId() == ((Button)v).getId())
		toggle(button07, colorArray, 0, 7);

	if (button10.getId() == ((Button)v).getId())
		toggle(button10, colorArray, 1, 0);
	else if (button11.getId() == ((Button)v).getId())
		toggle(button11, colorArray, 1, 1);
	else if (button12.getId() == ((Button)v).getId())
		toggle(button12, colorArray, 1, 2);
	else if (button13.getId() == ((Button)v).getId())
		toggle(button13, colorArray, 1, 3);
	else if (button14.getId() == ((Button)v).getId())
		toggle(button14, colorArray, 1, 4);
	else if (button15.getId() == ((Button)v).getId())
		toggle(button15, colorArray, 1, 5);
	else if (button16.getId() == ((Button)v).getId())
		toggle(button16, colorArray, 1, 6);
	else if (button17.getId() == ((Button)v).getId())
		toggle(button17, colorArray, 1, 7);
	
	if (button20.getId() == ((Button)v).getId())
		toggle(button20, colorArray, 2, 0);
	else if (button21.getId() == ((Button)v).getId())
		toggle(button21, colorArray, 2, 1);
	else if (button22.getId() == ((Button)v).getId())
		toggle(button22, colorArray, 2, 2);
	else if (button23.getId() == ((Button)v).getId())
		toggle(button23, colorArray, 2, 3);
	else if (button24.getId() == ((Button)v).getId())
		toggle(button24, colorArray, 2, 4);
	else if (button25.getId() == ((Button)v).getId())
		toggle(button25, colorArray, 2, 5);
	else if (button26.getId() == ((Button)v).getId())
		toggle(button26, colorArray, 2, 6);
	else if (button27.getId() == ((Button)v).getId())
		toggle(button27, colorArray, 2, 7);

	if (button30.getId() == ((Button)v).getId())
		toggle(button30, colorArray, 3, 0);
	else if (button31.getId() == ((Button)v).getId())
		toggle(button31, colorArray, 3, 1);
	else if (button32.getId() == ((Button)v).getId())
		toggle(button32, colorArray, 3, 2);
	else if (button33.getId() == ((Button)v).getId())
		toggle(button33, colorArray, 3, 3);
	else if (button34.getId() == ((Button)v).getId())
		toggle(button34, colorArray, 3, 4);
	else if (button35.getId() == ((Button)v).getId())
		toggle(button35, colorArray, 3, 5);
	else if (button36.getId() == ((Button)v).getId())
		toggle(button36, colorArray, 3, 6);
	else if (button37.getId() == ((Button)v).getId())
		toggle(button37, colorArray, 3, 7);

	if (button40.getId() == ((Button)v).getId())
		toggle(button40, colorArray, 4, 0);
	else if (button41.getId() == ((Button)v).getId())
		toggle(button41, colorArray, 4, 1);
	else if (button42.getId() == ((Button)v).getId())
		toggle(button42, colorArray, 4, 2);
	else if (button43.getId() == ((Button)v).getId())
		toggle(button43, colorArray, 4, 3);
	else if (button44.getId() == ((Button)v).getId())
		toggle(button44, colorArray, 4, 4);
	else if (button45.getId() == ((Button)v).getId())
		toggle(button45, colorArray, 4, 5);
	else if (button46.getId() == ((Button)v).getId())
		toggle(button46, colorArray, 4, 6);
	else if (button47.getId() == ((Button)v).getId())
		toggle(button47, colorArray, 4, 7);

	else if (button50.getId() == ((Button)v).getId())
		toggle(button50, colorArray, 5, 0);
	if (button51.getId() == ((Button)v).getId())
		toggle(button51, colorArray, 5, 1);
	else if (button52.getId() == ((Button)v).getId())
		toggle(button52, colorArray, 5, 2);
	else if (button53.getId() == ((Button)v).getId())
		toggle(button53, colorArray, 5, 3);
	else if (button54.getId() == ((Button)v).getId())
		toggle(button54, colorArray, 5, 4);
	else if (button55.getId() == ((Button)v).getId())
		toggle(button55, colorArray, 5, 5);
	else if (button56.getId() == ((Button)v).getId())
		toggle(button56, colorArray, 5, 6);
	else if (button57.getId() == ((Button)v).getId())
		toggle(button57, colorArray, 5, 7);

	if (button60.getId() == ((Button)v).getId())
		toggle(button60, colorArray, 6, 0);	
	else if (button61.getId() == ((Button)v).getId())
		toggle(button61, colorArray, 6, 1);
	else if (button62.getId() == ((Button)v).getId())
		toggle(button62, colorArray, 6, 2);
	else if (button63.getId() == ((Button)v).getId())
		toggle(button63, colorArray, 6, 3);
	else if (button64.getId() == ((Button)v).getId())
		toggle(button64, colorArray, 6, 4);
	else if (button65.getId() == ((Button)v).getId())
		toggle(button65, colorArray, 6, 5);
	else if (button66.getId() == ((Button)v).getId())
		toggle(button66, colorArray, 6, 6);
	else if (button67.getId() == ((Button)v).getId())
		toggle(button67, colorArray, 6, 7);

	if (button70.getId() == ((Button)v).getId())
		toggle(button70, colorArray, 7, 0);
	else if (button71.getId() == ((Button)v).getId())
		toggle(button71, colorArray, 7, 1);
	else if (button72.getId() == ((Button)v).getId())
		toggle(button72, colorArray, 7, 2);
	else if (button73.getId() == ((Button)v).getId())
		toggle(button73, colorArray, 7, 3);
	else if (button74.getId() == ((Button)v).getId())
		toggle(button74, colorArray, 7, 4);
	else if (button75.getId() == ((Button)v).getId())
		toggle(button75, colorArray, 7, 5);
	else if (button76.getId() == ((Button)v).getId())
		toggle(button76, colorArray, 7, 6);
	else if (button77.getId() == ((Button)v).getId())
		toggle(button77, colorArray, 7, 7);

}

/**
 * 
 * @param btn
 * @param colorArray
 * @param i x position
 * @param j y position
 */
public void toggle(Button btn, int[][] colorArray, int i, int j)
{
if (connected==false)	
{
	Toast.makeText(getApplicationContext(), "Please connect first", Toast.LENGTH_SHORT).show();
}
else
{
	if(colorArray[i][j]==0){
		colorArray[i][j] = 1;
		snd.setArray(colorArray);
		btn.setBackgroundColor(Color.RED);
	}
	else if(colorArray[i][j]==1){
		colorArray[i][j] = 2;
		snd.setArray(colorArray);
		btn.setBackgroundColor(Color.GREEN);
	}
	else if(colorArray[i][j]==2){
		colorArray[i][j] = 3;
		snd.setArray(colorArray);
		btn.setBackgroundColor(Color.BLUE);
	}
	else if(colorArray[i][j]==3){
		colorArray[i][j] = 0;
		snd.setArray(colorArray);
		btn.setBackgroundColor(Color.WHITE);
	}
}	
		
	
	
}

    
}
    
    


