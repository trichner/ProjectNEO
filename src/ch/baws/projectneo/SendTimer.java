package ch.baws.projectneo;

import java.util.TimerTask;

import ch.baws.projectneo.effects.Effect;

public class SendTimer extends TimerTask{
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
		if(array!=null && array.length==8 && array[0].length==8)
			bluetooth.send(array);
	}
	
	

}
