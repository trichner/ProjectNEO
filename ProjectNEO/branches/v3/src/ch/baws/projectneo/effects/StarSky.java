package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.R;

public class StarSky extends Effect{
	
	public StarSky(){
		super("ThomasR", "Star-Sky");
		this.icon = R.drawable.ic_eff_starsky;
	}
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
