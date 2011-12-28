package ch.baws.projectneo;

import ch.baws.projectneo.effects.*;
import ch.baws.projectneo.effects.Effect;
import android.app.Application;
import android.util.Log;

public class ProjectMORPHEUS extends Application{
	
	private static final String TAG = "ProjectMORPHEUS";
	private static final boolean D = true;	
	
	BluetoothUtils bluetooth = null;
	Effect effect = null;
	Effect defaultEffect = new DefaultEffect();
	
	@Override
	public void onCreate(){
		bluetooth = new BluetoothUtils();
		effect = defaultEffect;
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
		Effect oldEffect = this.effect;
		this.effect = effect;
		//only start the new if the old was started
		if(oldEffect.isAlive()){
			oldEffect.exit();
			if(!(this.effect.isAlive())){
				if(D) Log.e(TAG, "STARTEFFECT");
	        	this.effect.start();			
			}
		}
	}
	
	public void startEffect(){
		if(!effect.isAlive()) effect.start();
	}
	
	public void stopEffect(){
		effect.exit();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		effect.exit();
	}

	
	
}
