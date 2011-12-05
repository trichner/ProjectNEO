package ch.baws.projectneo;

import java.util.TimerTask;

public class SendTimer extends TimerTask{
	private BluetoothUtils bluetooth;
	private int[][] Array;
	

	public void setBluetooth(BluetoothUtils bluetooth) {
		this.bluetooth = bluetooth;
	}

	public void setArray(int[][] array) {
		Array = array;
	}


	public void run()
	{
		bluetooth.send(Array);
	}
	

}
