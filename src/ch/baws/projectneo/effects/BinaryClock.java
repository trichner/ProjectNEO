package ch.baws.projectneo.effects;

import java.sql.Time;

import ch.baws.projectneo.Frame;
import ch.baws.projectneo.GeneralUtils;
//doesn't work^^
public class BinaryClock extends Effect{

	@Override
	public int[][] getArray() {
		System.currentTimeMillis();
		Time time = new Time(System.currentTimeMillis());
		array = GeneralUtils.emptyArray(8, 8);
		int hours = time.getHours();
		int minutes = time.getMinutes();
		
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
		if((hours&0x03)==0x03){
			array[5][2] = Frame.NEO_WHITE;
		}
		if((hours&0x04)==0x04){
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
		
		         
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
