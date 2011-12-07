package ch.baws.projectneo.effects;

import java.util.Random;

public class Snake extends Thread{
	private class BodyPart{
		private int x;
		private int y;
		public BodyPart nextBody;
		public BodyPart(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	private static final boolean D = true;
	private static final String TAG = "SNAKE_THREAD";
	
	
	
	private BodyPart head;
	
	private Random rand;
	
	public Snake(){
		rand = new Random();
		head=new BodyPart(rand.nextInt(8), rand.nextInt(8));
		
		for(int i=0;i<3;i++){
			
		}
		
	}
	
	public void run(){
		
		
		//move and test if move is possible
		
		
		
		try {
			this.sleep(500);
		} catch (InterruptedException e) {
			
		}
	}
	
	public int[][] array(){
		
		
		
		
		return null;
	}
	
	public void turnLeft(){
		
	}
	public void turnRight(){
		
		
	}

}
