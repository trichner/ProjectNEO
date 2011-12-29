package ch.baws.projectneo.effects;

public class DefaultEffect extends Effect{
	Effect effect = new StarSky();
	@Override
	public int[][] getArray() {
		return effect.getArray();
	}

	@Override
	public void run() {
		effect.run();		
	}

}
