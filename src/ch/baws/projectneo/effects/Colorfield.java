package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;




public class Colorfield extends Effect{
	
	public Colorfield(){
		super("MarcelM", "Colorfield");
		array = GeneralUtils.fillArray(8,8, 2);
		delay = 100;
	}
	
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
		// TODO Auto-generated method stub
		
	}


}
