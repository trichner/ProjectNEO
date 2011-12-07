package ch.baws.projectneo;

import java.util.Timer;
import java.util.TimerTask;

public class WaveTimer extends TimerTask{
	private BluetoothUtils bluetooth;
	private int[][] array;
	private int line;
	private int color;
	private Timer timer;
	private SendTimer snd;;
	private boolean sndRunning = false;
	
	public void setBluetooth(BluetoothUtils bluetooth) {
		this.bluetooth = bluetooth;
	}
	
	public void setLine(int in_line) {
		this.line = in_line;
	}
	
	public void setArray(int[][] in_array) {
		this.array = in_array;
	}

	public void run()
	{
		if(line==8)
			line=0;
		if(color==4)
			color=0;
		for (int i=0;i<8;i++)
			array[line][i] = color;
		line++;
		if(line%8==0&&line!=0)
			color++;
		
		if(sndRunning==false){
		    timer = new Timer(); 
		    snd = new SendTimer();
		    snd.setBluetooth(bluetooth);	  
		    snd.setArray(array);
		    timer.schedule  ( snd, 1, 33 ); // frequency 30 fps
		    sndRunning=true;
		}
		

		
		//bluetooth.send(array);
	}
	
	public void sndcancel()
	{
		timer.cancel();
	}
	

}
