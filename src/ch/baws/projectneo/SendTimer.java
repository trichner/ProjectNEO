package ch.baws.projectneo;

import java.util.TimerTask;

public class SendTimer extends TimerTask{
	private BluetoothUtils bluetooth;
	private int[][] array;
	

	public void setBluetooth(BluetoothUtils bluetooth) {
		this.bluetooth = bluetooth;
	}

	public void setArray(int[][] in_array) {
		array = in_array;
	}


	public void run()
	{
		bluetooth.send(array);
	}
	
	

}
