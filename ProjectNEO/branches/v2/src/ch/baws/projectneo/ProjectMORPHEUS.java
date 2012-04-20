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
	Effect defaultEffect = new DefaultEffect();
	
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
		if(bluetooth==null) return null;
		return bluetooth.read();
	}
	
	public boolean bluetoothSend(int[][] arr){
		if(bluetooth==null) return false; //bluetoothConnect();
		return bluetooth.send(arr);
	}
	
	public boolean bluetoothConnect(){
		if(bluetooth==null) return false; //bluetooth = new BluetoothUtils();
		boolean error = bluetooth.connect();
		return error;
		//if(error) bluetooth =null;
	}
	
	public void bluetoothClose(){ //no need for return, don't care if something went wrong
		if(bluetooth==null) return;
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
		//only start the new if the service is running
		if(isServiceRunning){
			if(D) Log.e(TAG, "STARTEFFECT");
        	this.effect.start();
		}
	}
	
	public void startEffect(){
		effect.exit(); 				//stop any old threads, prevent zombies
		effect = new DefaultEffect();
		effect.start();
		if(D) Log.d(TAG, "started effect...");

	}
	
	public void stopEffect(){
		effect.exit();
		if(D) Log.d(TAG, "stopped effect...");
	}

	@Override
	public void onTerminate() {
		// Kill everything...
		super.onTerminate();
		effect.exit();
		stopService(new Intent(this,SendService.class));
	}

	public int[][] getEffectArray() {
		return effect.getArray();
	}
	
}
