package ch.baws.projectneo.effects;

import ch.baws.projectneo.ColorfieldActivity;
import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;




public class Colorfield extends Effect{
	
	public Colorfield(){
		super("MarcelM", "Colorfield");
		array = GeneralUtils.fillArray(8,8, 2);
		delay = 100;
		this.activity = ColorfieldActivity.class;
		icon = R.drawable.ic_eff_colorfield;
	}

	@Override
	public int[][] getArray() {
		return this.array;
	}
	
	public void setColor(int color)
	{	
		array = GeneralUtils.fillArray(8,8, color);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
