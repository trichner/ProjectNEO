package ch.baws.projectneo.effects;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

import ch.baws.projectneo.frameGenerator.*;
import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
//doesn't work^^
public class BinaryClock extends Effect{

	private static final String TAG = "Binary Clock";
	private static final boolean D = true;	
		
	public BinaryClock(){
		super("Thomas Richner", "Binary Clock");
		this.icon = R.drawable.ic_eff_binaryclock;
		
		if(D) Log.d(TAG,"New Binary Clock!");
		
		array = GeneralUtils.getEmpty8x8();
	}
	
	@Override
	public int[][] getArray() {
		this.interrupt();       
		return array;
	}

	@Override
	public void run() {

		while(!EXIT){
			array = GeneralUtils.getEmpty8x8();
			Date date = Calendar.getInstance().getTime();
			int hours = date.getHours();
			int minutes = date.getMinutes();
			int seconds = date.getSeconds();
			if(D) Log.d(TAG,"Hours: " + hours +"  Minutes: "+minutes);
			
			if(((hours/10)&0x01)==0x01){
				array[7][1] = Frame.NEO_WHITE;
			}
			if(((hours/10)&0x02)==0x02){
				array[6][1] = Frame.NEO_WHITE;
			}
			hours = hours%10;
			if((hours&0x01)==0x01){
				array[7][2] = Frame.NEO_WHITE;
			}
			if((hours&0x02)==0x02){
				array[6][2] = Frame.NEO_WHITE;
			}
			if((hours&0x04)==0x04){
				array[5][2] = Frame.NEO_WHITE;
			}
			if((hours&0x08)==0x08){
				array[4][2] = Frame.NEO_WHITE;
			}
			
			
			if(((minutes/60)&0x01)==0x01){
				array[7][5] = Frame.NEO_WHITE;
			}
			if(((minutes/60)&0x02)==0x02){
				array[6][5] = Frame.NEO_WHITE;
			}
			minutes %= 60;
			if(((minutes)&0x01)==0x01){
				array[7][6] = Frame.NEO_WHITE;
			}
			if(((minutes)&0x02)==0x02){
				array[6][6] = Frame.NEO_WHITE;
			}
			if(((minutes)&0x04)==0x04){
				array[5][6] = Frame.NEO_WHITE;
			}
			if(((minutes)&0x08)==0x08){
				array[4][6] = Frame.NEO_WHITE;
			}
			
			if(seconds%2==0){
				array[7][7] = Frame.NEO_GREEN;
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {	}
			
			
		}
		
	}

	
}
