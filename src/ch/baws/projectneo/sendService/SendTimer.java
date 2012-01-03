package ch.baws.projectneo.sendService;

import android.util.Log;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;

public class SendTimer implements Runnable{ //implements Runnable
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	private static final int RETRIES = 100;
	
	private long counter = 0;
	private int errorCount = 0;
	
	private ProjectMORPHEUS application;
	
	public SendTimer(ProjectMORPHEUS application){
		this.application = application;
	}

	@Override
	public void run()// throws ConnectionLostException
	{
		counter++;
		if (D)	Log.d(TAG, "SendTimer run...");
		int[][] arr = application.getEffectArray();
		boolean error=false;
		if(application.getBluetooth()!=null){
			try{
				//application.bluetoothRead();
				error = application.bluetoothSend(arr);
				if (D)	Log.d(TAG, "I've sent the array" + "  conter: " + counter);
				//in_buffer = application.bluetoothRead();
				//if(D) Log.d(TAG, "I read " + in_buffer.length + " bytes. First byte: " + Integer.toHexString(in_buffer[0]));
			} catch (RuntimeException e) {
				Log.e(TAG, "ON SEND: Runtime Exception, size: "+ arr.length + "*" + arr[0].length, e);				
			}
		}else{
			error = true;
			if (D)	Log.e(TAG, "not sending, no bluetooth");
		}	
		if(error){
			if(D) Log.e(TAG, "ERROR: Not able to send via Bluetooth");
			if(errorCount>RETRIES){
				application.bluetoothClose();
				//KILL ME NOW!
				errorCount=0; //or just keep trying...
			}else if(errorCount==0){
				//Try to connect again
				application.setBluetooth(new BluetoothUtils());
				application.bluetoothConnect();
			}
			errorCount++;
		}else{
			errorCount=0;
		}
		if (D)	Log.d(TAG, "... pause SendTimer run");
	}
	
}
