package ch.baws.projectneo;

import java.util.TimerTask;

import android.util.Log;

import ch.baws.projectneo.effects.Effect;

public class SendTimer extends TimerTask{
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	private BluetoothUtils bluetooth;
	
	private Effect effect;
	
	public SendTimer(Effect in_effect, BluetoothUtils in_bluetooth){
		this.effect = in_effect;
		this.bluetooth = in_bluetooth;
		
		if(!(this.effect.isAlive())){
        	this.effect.start();			
		}
	}
	
	public void setBluetooth(BluetoothUtils in_bluetooth) {
		this.bluetooth = in_bluetooth;
	}

	public void setEffect(Effect in_effect) {
		this.effect = in_effect;
	}

	public void run()
	{

		int[][] arr = effect.getArray();
		if (D)	Log.e(TAG, "try to send array, size: " + arr.length + "*" + arr[0].length);
		if(arr!=null && arr.length==8 && arr[0].length==8){
			if (D)	Log.e(TAG, "sending the array");
			try{
				this.bluetooth.send(arr);
			} catch (RuntimeException e) {
				Log.e(TAG, "ON SEND: Runtime Exception, size: "+ arr.length + "*" + arr[0].length, e);				
			}
		}
		else{
			if (D)	Log.e(TAG, "array is malformed");
		}
	}
	
	

}
