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

import ch.baws.projectneo.R;
import ch.baws.projectneo.effects.Buttons;
import ch.baws.projectneo.effects.Colorfield;
import ch.baws.projectneo.effects.RandomSnakePlayer;


import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class ProjectNEOActivity extends Activity {

	
	private static final String TAG = "PN_ACTIVITY";

	private static final boolean D = false;

	private int[][] colorArray; // array to store the current LED colors

	private SendTimer snd; // Timertask
	private Timer timer;
	private boolean timerisAlive = false; 
	
	public boolean connected = false;

	private OutputStream outStream = null;

	private BluetoothUtils Bluetooth = null;
	
	Buttons buttoneffect = new Buttons();
	
	Button[][] button;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        colorArray = GeneralUtils.emptyArray(8,8); // fills array with zeros
        button = new Button[8][8];
        
        
        button[0][0] = (Button) findViewById(R.id.button00);
        button[0][1] = (Button) findViewById(R.id.button01); // makes sure we can use buttonxx like a variable
        button[0][2] = (Button) findViewById(R.id.button02);
        button[0][3] = (Button) findViewById(R.id.button03);
        button[0][4] = (Button) findViewById(R.id.button04);
        button[0][5] = (Button) findViewById(R.id.button05);
        button[0][6] = (Button) findViewById(R.id.button06);
        button[0][7] = (Button) findViewById(R.id.button07);
 
        button[1][0] = (Button) findViewById(R.id.button10);
        button[1][1] = (Button) findViewById(R.id.button11); 
        button[1][2] = (Button) findViewById(R.id.button12);
        button[1][3] = (Button) findViewById(R.id.button13);
        button[1][4] = (Button) findViewById(R.id.button14);
        button[1][5] = (Button) findViewById(R.id.button15);
        button[1][6] = (Button) findViewById(R.id.button16);
        button[1][7] = (Button) findViewById(R.id.button17);
        
        button[2][0] = (Button) findViewById(R.id.button20);     
        button[2][1] = (Button) findViewById(R.id.button21);
        button[2][2] = (Button) findViewById(R.id.button22);
        button[2][3] = (Button) findViewById(R.id.button23);
        button[2][4] = (Button) findViewById(R.id.button24);
        button[2][5] = (Button) findViewById(R.id.button25);
        button[2][6] = (Button) findViewById(R.id.button26);
        button[2][7] = (Button) findViewById(R.id.button27);
        
        button[3][0] = (Button) findViewById(R.id.button30);
        button[3][1] = (Button) findViewById(R.id.button31);
        button[3][2] = (Button) findViewById(R.id.button32);
        button[3][3] = (Button) findViewById(R.id.button33);
        button[3][4] = (Button) findViewById(R.id.button34);
        button[3][5] = (Button) findViewById(R.id.button35);
        button[3][6] = (Button) findViewById(R.id.button36);
        button[3][7] = (Button) findViewById(R.id.button37);
        
        button[4][0] = (Button) findViewById(R.id.button40);
        button[4][1] = (Button) findViewById(R.id.button41);
        button[4][2] = (Button) findViewById(R.id.button42);
        button[4][3] = (Button) findViewById(R.id.button43);
        button[4][4] = (Button) findViewById(R.id.button44);
        button[4][5] = (Button) findViewById(R.id.button45);
        button[4][6] = (Button) findViewById(R.id.button46);
        button[4][7] = (Button) findViewById(R.id.button47);
        
        button[5][0] = (Button) findViewById(R.id.button50);
        button[5][1] = (Button) findViewById(R.id.button51);
        button[5][2] = (Button) findViewById(R.id.button52);
        button[5][3] = (Button) findViewById(R.id.button53);
        button[5][4] = (Button) findViewById(R.id.button54);
        button[5][5] = (Button) findViewById(R.id.button55);
        button[5][6] = (Button) findViewById(R.id.button56);
        button[5][7] = (Button) findViewById(R.id.button57);
        
        button[6][0] = (Button) findViewById(R.id.button60);
        button[6][1] = (Button) findViewById(R.id.button61);
        button[6][2] = (Button) findViewById(R.id.button62);
        button[6][3] = (Button) findViewById(R.id.button63);
        button[6][4] = (Button) findViewById(R.id.button64);
        button[6][5] = (Button) findViewById(R.id.button65);
        button[6][6] = (Button) findViewById(R.id.button66);
        button[6][7] = (Button) findViewById(R.id.button67);
        
        button[7][0] = (Button) findViewById(R.id.button70);
        button[7][1] = (Button) findViewById(R.id.button71);
        button[7][2] = (Button) findViewById(R.id.button72);
        button[7][3] = (Button) findViewById(R.id.button73);
        button[7][4] = (Button) findViewById(R.id.button74);
        button[7][5] = (Button) findViewById(R.id.button75);
        button[7][6] = (Button) findViewById(R.id.button76);
        button[7][7] = (Button) findViewById(R.id.button77);
        
        if(!D){
        	for(int m=0;m<8;m++){
        		for(int n=0;n<8;n++){
        			button[m][n].setText("");
    			}
        	}
        }
    

        
        Bluetooth = new BluetoothUtils();

        if (D)
        	Log.e(TAG, "+++ ON CREATE +++");
        
    	if (Bluetooth.init()==false) { //no BT adapter available
    		Toast.makeText(this, 
    			"You need Bluetooth in order to use this program", 
    			Toast.LENGTH_LONG).show();
    		//finish();
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
    	if(timerisAlive==true)
    	timer.cancel();
    	if(connected)
    		Bluetooth.close();
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
        	if (!connected) Bluetooth.connect();
        	Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        	connected = true;
      		
       		
       		
       		if(timerisAlive==true)
       		{
       			timer.cancel();
       			snd.cancel();
       		}  
       		timer = new Timer(); // daemon to send current colorcode
       		
        	Buttons buttoneffect = new Buttons();
       		buttoneffect.setArray(colorArray);
       		
       		timer = new Timer(); 
       		snd = new SendTimer(buttoneffect, Bluetooth);  
        	snd.setEffect(buttoneffect);
       		timer.schedule  ( snd, 100, 66 ); // frequency 15 fps
       		timerisAlive = true;
       		
       		

       		
       		Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
       		
       		
            return true;

        
        case R.id.effects:
        	final Intent intent = new Intent(this,EffectActivity.class);           
        	startActivity(intent);
        	if(timerisAlive==true)
        	timer.cancel();
        	if(connected) Bluetooth.close();
        	connected =false;
        	return true;
        
        }
        return false;
    }
/**
 * method toggleColor  
 * @param v
 */
public void toggleColor(View v){
	
	for(int m=0;m<8;m++){
		for(int n=0;n<8;n++){
			if(button[m][n].getId() == ((Button)v).getId()){
				toggle(button[m][n], colorArray, m, n);
			}
		}
	}

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
	switch(colorArray[i][j]){
	case 0:
		colorArray[i][j] = 1;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.RED);
		break;
	case 1:
		colorArray[i][j] = 2;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.GREEN);
		break;
	case 2:
		colorArray[i][j] = 3;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.BLUE);
		break;
	case 3:
		colorArray[i][j] = 4;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.YELLOW);
		break;
	case 4:
		colorArray[i][j] = 5;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.CYAN);
		break;
	case 5:
		colorArray[i][j] = 6;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.MAGENTA);
		break;
	case 6:
		colorArray[i][j] = 7;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.WHITE);
		break;
	case 7:
		colorArray[i][j] = 0;
		buttoneffect.setArray(colorArray);
		btn.setBackgroundColor(Color.GRAY);
		break;
	}
}	
		
	
	
}

    
}
    
    


