package ch.baws.projectneo;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;


import android.content.Intent;
import android.util.Log;

public class ProjectNEOActivity extends Activity {

	
	private static final String TAG = "PN_ACTIVITY";
	private static final boolean D = true;

	private OutputStream outStream = null;

	//private BluetoothUtils Bluetooth = null;  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
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

        BluetoothUtils Bluetooth = new BluetoothUtils();

    	// new stuff
        if (D)
        	Log.e(TAG, "+++ ON CREATE +++");
        
    	if (Bluetooth.Init()==false) { //no BT adapter available
    		Toast.makeText(this, 
    			"You need Bluetooth in order to use this program", 
    			Toast.LENGTH_LONG).show();
    		finish();
    		return;
    	}
    	
      if (!Bluetooth.Active()) {
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
       		String message = "Hello message from client to server.";
       		//BluetoothUtils.Send(message);
       		
       		
            return true;
        case R.id.color:
            // Ensure this device is discoverable by others
            //ensureDiscoverable();
            return true;
        }
        return false;
    }
    
}
    
    


