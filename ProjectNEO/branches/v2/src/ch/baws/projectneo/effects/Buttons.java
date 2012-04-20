package ch.baws.projectneo.effects;

import ch.baws.projectneo.ProjectNEOActivity;

public class Buttons extends Effect{
	
	public Buttons(){
		super("MarcelM","Fun with Buttons!");
		activity = ProjectNEOActivity.class;
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
