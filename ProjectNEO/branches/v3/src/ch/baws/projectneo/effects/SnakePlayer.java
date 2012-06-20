package ch.baws.projectneo.effects;

import android.util.Log;
import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
import ch.baws.projectneo.SnakeActivity;
import ch.baws.projectneo.effects.Snake.Dir;

public class SnakePlayer extends Effect {

	protected static final String TAG = "SNAKEPLAYER";
	protected static final boolean D = true;
	
	private Snake snake = new Snake();
	
	public SnakePlayer() {
		super("Philipp Boesch","Snake!");
		this.icon = R.drawable.ic_eff_snake; //TODO cool icon!
		this.activity = SnakeActivity.class;
	}
	
	@Override
	public int[][] getArray() {
		if(D) Log.d(TAG,"getting Array...");
		int[][] arr = snake.getArray();
		if(D) Log.d(TAG,"got Array!");
		return arr;
	}
	public void setDir(Dir dir) {
		snake.setDir(dir);
	}

	@Override
	public void run() {
		snake.setSpeed(800);
		snake.start();
		while(!EXIT){
			
			try {
				//sleep(1000000000);
				sleep(100);
			} catch (InterruptedException e) {

			}
		}
		
	}
	
	

}
