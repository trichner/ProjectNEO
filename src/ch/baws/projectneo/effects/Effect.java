package ch.baws.projectneo.effects;

import ch.baws.projectneo.EffectActivity;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected boolean EXIT = false;
	protected EffectActivity ea;
	
	/**
	 * @return the array
	 */	
	public abstract int[][] getArray();
	
	public void setEffectActivity(EffectActivity in_ea){
		 this.ea = in_ea;
	}
	
	
	public void exit(){
		EXIT = true;
	}
	
	public abstract void run();

}
