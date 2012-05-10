package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
import ch.baws.projectneo.SnakeActivity;
import ch.baws.projectneo.effects.Snake.Dir;

public class SnakePlayer extends Effect {

	private Snake snake = new Snake();
	
	public SnakePlayer() {
		super("Philipp Boesch","Snake!");
		this.icon = R.drawable.ic_eff_snake; //TODO cool icon!
		this.activity = SnakeActivity.class;
	}
	
	@Override
	public int[][] getArray() {
		//TODO
		return snake.getArray();
	}
	public void setDir(Dir dir) {
		snake.setDir(dir);
	}

	@Override
	public void run() {
		
		snake.start();
		while(!EXIT){
			
			try {
				sleep(1000000000);
			} catch (InterruptedException e) {

			}
		}
		
	}
	
	

}
