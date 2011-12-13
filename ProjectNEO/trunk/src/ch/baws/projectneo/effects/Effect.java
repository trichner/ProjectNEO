package ch.baws.projectneo.effects;

import ch.baws.projectneo.EffectActivity;
import ch.baws.projectneo.GeneralUtils;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected boolean EXIT = false;
	protected EffectActivity ea;
	
	protected static final String TAG = "EFFECTS";
	protected static final boolean D = false;
	
	
	public Effect(){
		this.array = GeneralUtils.emptyArray(8, 8);
	}
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
