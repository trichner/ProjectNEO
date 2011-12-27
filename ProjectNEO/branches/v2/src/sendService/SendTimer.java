package sendService;

import java.util.TimerTask;

import android.util.Log;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;
import ch.baws.projectneo.effects.*;

public class SendTimer implements Runnable{ //implements Runnable
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	//private static boolean FLAG_RUN = false;
	private long counter = 0;
	//stops at 2422 with 20fps
	//stops at 2411 with 10fps
	
	private ProjectMORPHEUS application;
	
	private Effect effect;
	private Effect defaultEffect = new StarSky();
	
	public SendTimer(ProjectMORPHEUS application){
		this.effect = defaultEffect;
		this.application = application;
		if(!(this.effect.isAlive())){
			if(D) Log.e(TAG, "STARTEFFECT");
        	this.effect.start();			
		}
	}
	
	public SendTimer(ProjectMORPHEUS application,Effect effect){
		this.effect = effect;
		this.application = application;
		if(!(this.effect.isAlive())){
			if(D) Log.e(TAG, "STARTEFFECT");
        	this.effect.start();			
		}
	}

	public void setEffect(Effect in_effect) {
		
		if(effect.isAlive()) effect.exit();
		
		this.effect = in_effect;
		
		if(!(this.effect.isAlive())){
			if(D) Log.e(TAG, "STARTEFFECT");
        	this.effect.start();			
		}
	}
	
	public void stopEffect() {
		if(effect.isAlive()) effect.exit();
	}

	public void run()
	{
		//if(FLAG_RUN) Log.d(TAG,"!!! Already running !!!");
		//FLAG_RUN = true;
		counter++;
		byte[] in_buffer;
		
		int[][] arr = effect.getArray();
		
		//if (D)	Log.e(TAG, "try to send array, size: " + arr.length + "*" + arr[0].length);
		
		//if(arr!=null && arr.length==8 && arr[0].length==8){ //makes no sense, if the array is malformed, there's somewhere a fatal error
			
			if(application.getBluetooth()!=null){
				
				try{
					//if((counter&0x01)==0) 
					application.getBluetooth().send(arr);
					if (D)	Log.e(TAG, "I've sent the array" + "  conter: " + counter);
					in_buffer = application.getBluetooth().read();
					if(D) Log.e(TAG, "I read " + in_buffer.length + " bytes. First byte: " + Integer.toHexString(in_buffer[0]));
				} catch (RuntimeException e) {
					Log.e(TAG, "ON SEND: Runtime Exception, size: "+ arr.length + "*" + arr[0].length, e);				
				}
			}else{
				if (D)	Log.e(TAG, "not sending, in emulator mode");
			}	
			
			
		/*}
		else{
			if (D)	Log.e(TAG, "array is malformed");
		}*/
		//FLAG_RUN = false;
	}
	
	

}
