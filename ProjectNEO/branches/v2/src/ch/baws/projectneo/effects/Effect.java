package ch.baws.projectneo.effects;

import ch.baws.projectneo.EffectActivity;
import ch.baws.projectneo.GeneralUtils;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected boolean EXIT = false;
	protected EffectActivity ea;
	
	protected static final String TAG = "EFFECTS";
	protected static final boolean D = false;
	
	public final String author;
	public final String title;
	
	public Effect(String author,String title){
		this.array = GeneralUtils.emptyArray(8, 8);
		this.author = author;
		this.title = title;
	}
	
	public Effect(){
		this.array = GeneralUtils.emptyArray(8, 8);
		author = "brownies";
		title = "CookieEffect";	
	}
	
	/**
	 * @return the array
	 */	
	public abstract int[][] getArray();
	
	
	public void exit(){
		EXIT = true;
	}
	
	public abstract void run();

}
