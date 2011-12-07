package ch.baws.projectneo.effects;

import java.util.Timer;

import ch.baws.projectneo.BluetoothUtils;

public class Wave {
	
	
	public static boolean start(BluetoothUtils Bluetooth, Timer timer, WaveTimer wave)
	{

		wave.setBluetooth(Bluetooth);
		int[][] colorArray = new int[8][8];
		wave.setArray(colorArray);
		timer.schedule  ( wave, 1000, 1000 ); // frequency 1 fps
		return true;
	}

}
