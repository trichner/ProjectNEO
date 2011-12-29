package ch.baws.projectneo.sendService;

import android.util.Log;

import ch.baws.projectneo.ProjectMORPHEUS;

public class SendTimer implements Runnable{ //implements Runnable
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	private long counter = 0;
	
	private ProjectMORPHEUS application;
	
	public SendTimer(ProjectMORPHEUS application){
		this.application = application;
	}

	public void run()
	{
		counter++;
		if (D)	Log.d(TAG, "SendTimer run...");
		int[][] arr = application.getEffectArray();

		if(application.getBluetooth()!=null){
			try{
				//application.bluetoothRead();
				application.bluetoothSend(arr);
				if (D)	Log.d(TAG, "I've sent the array" + "  conter: " + counter);
				//in_buffer = application.bluetoothRead();
				//if(D) Log.d(TAG, "I read " + in_buffer.length + " bytes. First byte: " + Integer.toHexString(in_buffer[0]));
			} catch (RuntimeException e) {
				Log.e(TAG, "ON SEND: Runtime Exception, size: "+ arr.length + "*" + arr[0].length, e);				
			}
		}else{
			if (D)	Log.e(TAG, "not sending, no bluetooth");
		}	
		if (D)	Log.d(TAG, "... pause SendTimer run");
	}
	
}
