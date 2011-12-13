package ch.baws.projectneo.effects;

import java.util.Random;

public class StarSky extends Effect{

	synchronized public int[][] getArray() {
		return array;
	}

	public void run() {
		Random rand = new Random();
		while(!EXIT){
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					array[i][j] = rand.nextInt(8);
				}
			}	
			try {
				sleep(500);
			} catch (InterruptedException e) {}
		}
	}

}
