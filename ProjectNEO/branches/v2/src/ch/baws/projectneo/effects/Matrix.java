package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.Frame;
import ch.baws.projectneo.GeneralUtils;

public class Matrix extends Effect{
	
	private class Strip{
		public Strip(double position,double speed,int length){
			this.position = position;
			this.speed = speed;
			this.length = length;
		}
		public void step(){
			if(length>0)
				position += speed;
		}
		double position;
		double speed;
		int length;
	}
	private static final int COLOR = Frame.NEO_GREEN;
	Strip[] strips= new Strip[8];
	
	public Matrix(){
		super("ThomasR", "Matrix Screen");
		Random rand = new Random();
		for(int i=0;i<8;i++){
			strips[i] = new Strip(0,rand.nextDouble()+0.5,rand.nextInt(5)+2);
		}
	}
	
	@Override
	public int[][] getArray() {
		array = GeneralUtils.emptyArray(8, 8);
		for(int i=0;i<8;i++){
			for(int j=0;(strips[i].position)>j;j++){
				if(((strips[i].position-strips[i].length)<j)&&(j<8)){
					array[j][i] = COLOR;
				}
			}
		}
		return array;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		while(!EXIT){
			
			for(int i=0;i<8;i++){
				strips[i].step();
				if((strips[i].position-strips[i].length)>8){
					strips[i] = new Strip(0,rand.nextDouble()+0.7,rand.nextInt(5)+2);
				}
			}
			
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
		
	}

}
