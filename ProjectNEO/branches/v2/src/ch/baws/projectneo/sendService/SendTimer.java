package ch.baws.projectneo.sendService;

import java.util.TimerTask;

import android.util.Log;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;
import ch.baws.projectneo.effects.*;

public class SendTimer implements Runnable{ //implements Runnable
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	private long counter = 0;
	
	private ProjectMORPHEUS application;
	private BluetoothUtils bluetooth;
	
	public SendTimer(ProjectMORPHEUS application){
		this.application = application;
		bluetooth = application.getBluetooth();
	}

	public void run()
	{
		counter++;
		byte[] in_buffer;
		
		int[][] arr = application.getEffect().getArray();

		if(bluetooth!=null){
			
			try{
				//if((counter&0x01)==0) 
				bluetooth.read();
				bluetooth.send(arr);
				if (D)	Log.e(TAG, "I've sent the array" + "  conter: " + counter);
				in_buffer = bluetooth.read();
				if(D) Log.e(TAG, "I read " + in_buffer.length + " bytes. First byte: " + Integer.toHexString(in_buffer[0]));
			} catch (RuntimeException e) {
				Log.e(TAG, "ON SEND: Runtime Exception, size: "+ arr.length + "*" + arr[0].length, e);				
			}
		}else{
			if (D)	Log.e(TAG, "not sending, no bluetooth");
		}	
	}
	
}
