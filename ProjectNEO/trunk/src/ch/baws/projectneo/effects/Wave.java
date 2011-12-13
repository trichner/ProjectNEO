package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;


public class Wave extends Effect{
	

	@Override
	public int[][] getArray() {
		return this.array;
	}

	@Override
	public void run() {
		
		
		this.array = new int[8][8];
		this.array = GeneralUtils.emptyArray(8, 8);
		int line = 0;
		int color = 0;
		
		
		
		while(!EXIT){
			if(line==8)
				line=0;
			if(color==8)
				color=0;
			for (int i=0;i<8;i++)
			{
				this.array[line][i] = color;
			}
			line++;
			if(line%8==0&&line!=0)
				color++;
			//ea.draw(array); //prints the array
			try {
				sleep(250);
			} catch (InterruptedException e) {}
		}
		
	}

}
