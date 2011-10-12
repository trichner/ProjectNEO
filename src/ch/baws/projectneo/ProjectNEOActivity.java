package ch.baws.projectneo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProjectNEOActivity extends Activity {
	
	/** Id des Menüeintrags. */
	public static final int SEND_ID = Menu.FIRST;
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(0, SEND_ID, Menu.NONE, "send");
      return super.onCreateOptionsMenu(menu);
      
    }
    
    
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//          case SEND_ID:
//        	  break;
//        	  
//        	  default:
        		  
        	  
}