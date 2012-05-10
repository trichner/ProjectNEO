package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
import ch.baws.projectneo.SnakeActivity;

public class SnakePlayer extends Effect {

	public SnakePlayer() {
		super("Philipp Boesch","Snake!");
		this.icon = R.drawable.ic_eff_snake; //TODO cool icon!
		this.activity = SnakeActivity.class;
	}
	
	@Override
	public int[][] getArray() {
		//TODO
		return GeneralUtils.getEmpty8x8();
	}

	@Override
	public void run() {
		
		while(!EXIT){
			
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		
	}

}
