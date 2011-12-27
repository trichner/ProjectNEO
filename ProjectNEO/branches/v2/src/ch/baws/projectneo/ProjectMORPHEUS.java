package ch.baws.projectneo;

import ch.baws.projectneo.effects.Effect;
import android.app.Application;

public class ProjectMORPHEUS extends Application{
	
	BluetoothUtils bluetooth = null;
	Effect effect = null;
	
	@Override
	public void onCreate(){
		bluetooth = new BluetoothUtils();
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

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	
	
}
