package ch.baws.projectneo.effects;

public abstract class Effect extends Thread{
	
	protected int[][] array;
	protected boolean EXIT = false;
	
	/**
	 * @return the array
	 */
	public abstract int[][] getArray();

	public void exit(){
		EXIT = true;
	}
	
	public abstract void run();

}
