package ch.baws.projectneo.effects;

import ch.baws.projectneo.EffectDrawer;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected boolean EXIT = false;
	protected EffectDrawer ed;
	
	/**
	 * @return the array
	 */	
	public abstract int[][] getArray();
	
	public void setEffectDrawer(EffectDrawer in_ed){
		 this.ed = in_ed;
	}
	
	
	public void exit(){
		EXIT = true;
	}
	
	public abstract void run();

}
