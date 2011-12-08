package ch.baws.projectneo;

import java.util.TimerTask;

import android.util.Log;

import ch.baws.projectneo.effects.Effect;

public class SendTimer extends TimerTask{
	
	private static final String TAG = "SEND_TIMER";
	private static final boolean D = true;	
	
	private BluetoothUtils bluetooth;
	
	private Effect effect;
	
	public SendTimer(Effect in_effect){
		this.effect = in_effect;
		
		if(!(this.effect.isAlive())){
        	this.effect.start();			
		}
	}
	
	public void setBluetooth(BluetoothUtils bluetooth) {
		this.bluetooth = bluetooth;
	}


	public void run()
	{
		int[][] array = effect.getArray();
		if (D)	Log.e(TAG, "try to send array, size: " + array.length + "*" + array[0].length);
		if(array!=null && array.length==8 && array[0].length==8){
			if (D)	Log.e(TAG, "sending the array");
			bluetooth.send(array);
		}else{
			if (D)	Log.e(TAG, "array is malformed");
		}
	}
	
	

}
