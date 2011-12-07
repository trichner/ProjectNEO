package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.GeneralUtils;

public class StarSky extends Effect{
	int[][] array;
	
	public StarSky(){
		array = GeneralUtils.emptyArray(8, 8);
	}
	public int[][] getArray() {
		return array;
	}

	public void run() {
		Random rand = new Random();
		while(!EXIT){
			int[][] array = new int[8][8];
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					array[i][j] = rand.nextInt(4);
				}
			}	
			
			try {
				sleep(500);
			} catch (InterruptedException e) {}
		}
	}

}
