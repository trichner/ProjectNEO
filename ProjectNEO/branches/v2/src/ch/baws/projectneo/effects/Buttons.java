package ch.baws.projectneo.effects;

import ch.baws.projectneo.ProjectNEOActivity;
import ch.baws.projectneo.R;

public class Buttons extends Effect{
	
	public Buttons(){
		super("MarcelM","Fun with Buttons!");
		activity = ProjectNEOActivity.class;
		icon = R.drawable.ic_eff_button;
	}
	
	@Override
	public int[][] getArray() {
		return array;
	}

	
	public void setArray(int[][] in_array) {
		this.array = in_array;
	}
	@Override
	public void run() {
		
	}

}
