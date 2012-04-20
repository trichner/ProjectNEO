package ch.baws.projectneo.effects;

import ch.baws.projectneo.GeneralUtils;

public abstract class Effect extends Thread{
	
	protected volatile int[][] array;
	protected boolean EXIT = false;
	protected int delay = 0;
	
	protected static final String TAG = "EFFECT";
	protected static final boolean D = false;
	protected Class activity = null;
	
	public final String AUTHOR;
	public final String TITLE;
	
	public Effect(String author,String title){
		this.array = GeneralUtils.emptyArray(8, 8);
		this.AUTHOR = author;
		this.TITLE = title;
	}
	
	public Effect(){
		this.array = GeneralUtils.emptyArray(8, 8);
		AUTHOR = "busy beaver";
		TITLE = "CookieEffect";	
	}
	
	/**
	 * @return the array
	 */	
	public abstract int[][] getArray();
	
	
	public void exit(){
		EXIT = true;
	}
	
	public boolean hasAnActivity(){
		return activity!=null;
	}
	
	public Class getActivity(){
		return activity;
	}
		
	public abstract void run();	
}
