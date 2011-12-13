package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;




public class Colorfield extends Effect{
	
	int color=0;
	@Override
	public int[][] getArray() {
		return this.array;
	}
	
	public void setColor(int c)
	{
		this.color = c;
	}

	@Override
	public void run() {
		
		while(!EXIT){
			array = GeneralUtils.fillArray(8,8, color);
			//ea.draw(array); //prints the array
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
		
	}

}
