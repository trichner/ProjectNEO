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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class ProjectNEOActivity extends Activity {

	
	private static final String TAG = "PN_ACTIVITY";
	private static final boolean D = true;
	public int[][] colorArray;

	private OutputStream outStream = null;

	private BluetoothUtils Bluetooth = null;  
	Button button11, button12, button13, button14, button15, button16, button17, button18;
	Button button21, button22, button23, button24, button25, button26, button27, button28;
	Button button31, button32, button33, button34, button35, button36, button37, button38;
	Button button41, button42, button43, button44, button45, button46, button47, button48;
	Button button51, button52, button53, button54, button55, button56, button57, button58;
	Button button61, button62, button63, button64, button65, button66, button67, button68;
	Button button71, button72, button73, button74, button75, button76, button77, button78;
	Button button81, button82, button83, button84, button85, button86, button87, button88;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        
//        // If the adapter is null, then Bluetooth is not supported
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
//            finish();
//            return;
//        }
//        
        String FILENAME = "hello_file";
        String string = "hello world!";

//        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//        fos.write(string.getBytes());
//        fos.close();
        
       colorArray = new int[9][9];
        for (int i=0;i<9;i++){
        	for (int j=0;j<9;j++){
        		colorArray[i][j] = 0;
        	}
        }	
        
        
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);
        
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button24 = (Button) findViewById(R.id.button24);
        button25 = (Button) findViewById(R.id.button25);
        button26 = (Button) findViewById(R.id.button26);
        button27 = (Button) findViewById(R.id.button27);
        button28 = (Button) findViewById(R.id.button28);
        
        button31 = (Button) findViewById(R.id.button31);
        button32 = (Button) findViewById(R.id.button32);
        button33 = (Button) findViewById(R.id.button33);
        button34 = (Button) findViewById(R.id.button34);
        button35 = (Button) findViewById(R.id.button35);
        button36 = (Button) findViewById(R.id.button36);
        button37 = (Button) findViewById(R.id.button37);
        button38 = (Button) findViewById(R.id.button38);
        
        button41 = (Button) findViewById(R.id.button41);
        button42 = (Button) findViewById(R.id.button42);
        button43 = (Button) findViewById(R.id.button43);
        button44 = (Button) findViewById(R.id.button44);
        button45 = (Button) findViewById(R.id.button45);
        button46 = (Button) findViewById(R.id.button46);
        button47 = (Button) findViewById(R.id.button47);
        button48 = (Button) findViewById(R.id.button48);
        
        button51 = (Button) findViewById(R.id.button51);
        button52 = (Button) findViewById(R.id.button52);
        button53 = (Button) findViewById(R.id.button53);
        button54 = (Button) findViewById(R.id.button54);
        button55 = (Button) findViewById(R.id.button55);
        button56 = (Button) findViewById(R.id.button56);
        button57 = (Button) findViewById(R.id.button57);
        button58 = (Button) findViewById(R.id.button58);
        
        button61 = (Button) findViewById(R.id.button61);
        button62 = (Button) findViewById(R.id.button62);
        button63 = (Button) findViewById(R.id.button63);
        button64 = (Button) findViewById(R.id.button64);
        button65 = (Button) findViewById(R.id.button65);
        button66 = (Button) findViewById(R.id.button66);
        button67 = (Button) findViewById(R.id.button67);
        button68 = (Button) findViewById(R.id.button68);
        
        button71 = (Button) findViewById(R.id.button71);
        button72 = (Button) findViewById(R.id.button72);
        button73 = (Button) findViewById(R.id.button73);
        button74 = (Button) findViewById(R.id.button74);
        button75 = (Button) findViewById(R.id.button75);
        button76 = (Button) findViewById(R.id.button76);
        button77 = (Button) findViewById(R.id.button77);
        button78 = (Button) findViewById(R.id.button78);
        
        button81 = (Button) findViewById(R.id.button81);
        button82 = (Button) findViewById(R.id.button82);
        button83 = (Button) findViewById(R.id.button83);
        button84 = (Button) findViewById(R.id.button84);
        button85 = (Button) findViewById(R.id.button85);
        button86 = (Button) findViewById(R.id.button86);
        button87 = (Button) findViewById(R.id.button87);
        button88 = (Button) findViewById(R.id.button88);
        
        
        Bluetooth = new BluetoothUtils();

    	// new stuff
        if (D)
        	Log.e(TAG, "+++ ON CREATE +++");
        
    	if (Bluetooth.init()==false) { //no BT adapter available
    		Toast.makeText(this, 
    			"You need Bluetooth in order to use this program", 
    			Toast.LENGTH_LONG).show();
    		finish();
    		return;
    	}
    	
      if (!Bluetooth.active()) {
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
        case R.id.send:
       		if (D) {
       			Log.e(TAG, "+ SEND BUTTON SELECT +");
       			Log.e(TAG, "+ ABOUT TO ATTEMPT CLIENT CONNECT +");
       		}
       		//String message = "Hello message from client to server.";
       		
       		Frame frame = new Frame();
       		Bluetooth.send(frame);
       		
       		
            return true;
        case R.id.color:
            // Ensure this device is discoverable by others
            //ensureDiscoverable();
            return true;
        }
        return false;
    }
    
public void toggleColor(View v){
	
	if (button11.getId() == ((Button)v).getId())
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
	else if (button18.getId() == ((Button)v).getId())
		toggle(button18, colorArray, 1, 8);
	
	if (button21.getId() == ((Button)v).getId())
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
	else if (button28.getId() == ((Button)v).getId())
		toggle(button28, colorArray, 2, 8);
	
	
}

public void toggle(Button btn, int[][] colorArray, int i, int j)
{
	
	if(colorArray[i][j]==0){
		colorArray[i][j] = 1;
		btn.setBackgroundColor(Color.RED);
	}
	else if(colorArray[i][j]==1){
		colorArray[i][j] = 2;
		btn.setBackgroundColor(Color.GREEN);
	}
	else if(colorArray[i][j]==2){
		colorArray[i][j] = 3;
		btn.setBackgroundColor(Color.BLUE);
	}
	else if(colorArray[i][j]==3){
		colorArray[i][j] = 0;
		btn.setBackgroundColor(Color.WHITE);
	}
		
		
	
	
}
    
}
    
    


