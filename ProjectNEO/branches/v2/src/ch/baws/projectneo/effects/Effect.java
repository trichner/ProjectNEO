package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected volatile boolean EXIT = false;
	protected int delay = 0;
	
	protected static final String TAG = "EFFECT";
	protected static final boolean D = false;
	protected Class activity = null;
	protected int icon = R.drawable.ic_app;
	
	public final String AUTHOR;
	public final String TITLE;
	
	public Effect(String author,String title){
		this.array = GeneralUtils.emptyArray(8, 8);
		this.AUTHOR = author;
		this.TITLE = title;
	}
	
	public Effect(){
		this.array = GeneralUtils.emptyArray(8, 8);
		AUTHOR = "Brownie";
		TITLE = "The Cake is a Lie!";	
	}
	
	/**
	 * @return the array
	 */	
	public abstract int[][] getArray();
	
	
	public void exit(){
		this.interrupt();
		EXIT = true;
	}
	
	public boolean hasAnActivity(){
		return activity!=null;
	}
	
	public Class getActivity(){
		return activity;
	}
	
	public int getIcon(){
		return this.icon;
	}
		
	public abstract void run();	
}
