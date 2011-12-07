package ch.baws.projectneo.effects;

import java.util.Random;

public class Snake extends Thread{
	private class BodyPart{
		int x;
		int y;
		BodyPart nextBody;
		public BodyPart(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	private static final boolean D = true;
	private static final String TAG = "SNAKE_THREAD";
	
	private boolean EXIT = false;
	
	public enum Dir{RIGHT,LEFT,UP,DOWN};
	
	private BodyPart head;
	
	private Random rand;
	
	private Dir dir;
	
	public Snake(){
		create();
	}
	
	private void create(){
		rand = new Random();
		head=new BodyPart(4, 4);
		dir = Dir.DOWN;
		
		BodyPart temp1,temp2;
		temp2 = head;
		for(int i=0;i<3;i++){
			temp1 = new BodyPart(head.x+1,head.y);
			temp2.nextBody = temp1;
			temp2 = temp1;
		}
	}
	
	
	public boolean isValidMove(Dir direction){
		switch(dir){
		case    UP:
			if(head.y==7)
				return false;
			else
				return true;
		case RIGHT:
			if(head.x==7)
				return false;
			else
				return true;
		case  DOWN:
			if(head.y==0)
				return false;
			else
				return true;
		case  LEFT:
			if(head.x==0)
				return false;
			else
				return true;
		}
		return false;
	}
	
	public void run(){
		
		
		//move and test if move is possible
		BodyPart next = null;
		while(!EXIT){
			//Game Over ?
			if(!isValidMove(dir)){
				create();
			}
			
			switch(dir){
			case    UP:
				next = new BodyPart(head.x,head.y+1);
				break;
			case RIGHT:
				next = new BodyPart(head.x+1,head.y);
				break;
			case  DOWN:
				next = new BodyPart(head.x,head.y-1);
					break;
			case  LEFT:
				next = new BodyPart(head.x-1,head.y);
				break;
			}
	
			next.nextBody = head;
			head = next;		
		
			//Speed of Snake :)
			try {
				this.sleep(500);
			} catch (InterruptedException e) {	}
		}
	}
	
	public void exit(){
		EXIT = true;
	}
	
	public void turnLeft(){
		
	}
	public void turnRight(){
			
	}

}
