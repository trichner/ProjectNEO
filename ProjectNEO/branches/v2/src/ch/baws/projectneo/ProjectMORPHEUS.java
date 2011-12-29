package ch.baws.projectneo;

import ch.baws.projectneo.effects.*;
import ch.baws.projectneo.sendService.SendService;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class ProjectMORPHEUS extends Application{
	
	private static final String TAG = "ProjectMORPHEUS";
	private static final boolean D = true;	
	
	BluetoothUtils bluetooth = null;
	Effect effect = null;
	Effect defaultEffect = new StarSky();
	
	private boolean isServiceRunning;
	
	public boolean isServiceRunning(){
		return isServiceRunning;
	}
	
	public void setServiceRunning(boolean isServiceRunning){
		this.isServiceRunning = isServiceRunning;
	}
	
	@Override
	public void onCreate(){
		bluetooth = new BluetoothUtils();
		effect = defaultEffect;
	}
	
	public byte[] bluetoothRead(){
		return bluetooth.read();
	}
	
	public void bluetoothSend(int[][] arr){
		bluetooth.send(arr);
	}
	
	public void bluetoothConnect(){
		bluetooth.connect();
	}
	
	public void bluetoothClose(){
		bluetooth.close();
	}

	public BluetoothUtils getBluetooth() {
		return bluetooth;
	}

	public void setBluetooth(BluetoothUtils bluetooth) {
		this.bluetooth = bluetooth;
	}

	public Effect getEffect() {
		return effect;
	}
	
	public boolean effectIsAlive(){
		return effect.isAlive();
	}

	public void setEffect(Effect effect) {
		this.effect.exit();
		this.effect = effect;
		//only start the new if the old was started
		if(isServiceRunning){
			if(!(this.effect.isAlive())){
				if(D) Log.e(TAG, "STARTEFFECT");
	        	this.effect.start();			
			}
		}
	}
	
	public void startEffect(){
		if(effect.getState()==Thread.State.TERMINATED){
			if(D) Log.e(TAG, "ERROR:Effect was somewhere terminated");
			effect = new DefaultEffect();
		}
		effect.start();
		if(D) Log.d(TAG, "started effect...");

	}
	
	public void stopEffect(){
		effect.exit();
		if(D) Log.d(TAG, "stopped effect...");
	}
	
	public void suspendEffect(){
		effect.suspend();
	}
	
	public void resumeEffect(){
		effect.resume();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		effect.exit();
		stopService(new Intent(this,SendService.class));
	}

	public int[][] getEffectArray() {
		return effect.getArray();
	}

	
	
}
