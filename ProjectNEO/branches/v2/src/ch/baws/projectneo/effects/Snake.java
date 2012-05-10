package ch.baws.projectneo.effects;

import java.util.Random;
import ch.baws.projectneo.frameGenerator.*;
import ch.baws.projectneo.GeneralUtils;

public class Snake extends Thread{
	
	private int SPEED = 400;
	
	private class BodyPart{
		int x;
		int y;
		BodyPart nextBody;
		public BodyPart(int x, int y){
			this.x = x;
			this.y = y;
		}
		public boolean equals(BodyPart body){
			return ((this.x==body.x) && (this.y==body.y));
		}
	}
	private class Food{
		int x;
		int y;
		public void generate(){
			x = rand.nextInt(8);
			y = rand.nextInt(8);
			BodyPart temp=head;
			
			while(temp.nextBody != null){
				if(temp.x==x && temp.y == y){
					generate();
					temp = head;
				}else{
					temp = temp.nextBody;
				}
			}
		}
	}
	
	private static final boolean D = true;
	private static final String TAG = "SNAKE_THREAD";
	
	private static final int COLOR_SNAKE = Frame.NEO_RED; //Red
	private static final int COLOR_FOOD  = Frame.NEO_BLUE; //Blue
	
	public enum Dir{RIGHT,LEFT,UP,DOWN};
	
	private boolean EXIT = false;
	private BodyPart head;
	private Food food;
	
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
		food = new Food();
		food.generate();
	}
	
	
	public boolean isValidMove(Dir direction){
		BodyPart next = null;
		//running out of bounds?
		switch(dir){
		case    UP:
			if(head.y==0)
				return false;
			next = new BodyPart(head.x-1,head.y);
			break;
		case RIGHT:
			if(head.x==7)
				return false;
			next = new BodyPart(head.x,head.y+1);
			break;
		case  DOWN:
			if(head.y==7)
				return false;
			next = new BodyPart(head.x+1,head.y);
			break;
		case  LEFT:
			if(head.x==0)
				return false;
			next = new BodyPart(head.x,head.y-1);
			break;
		}

		//Test if eating itself
		BodyPart temp=head;
		while(temp!=null){
			if(next.equals(temp)){
				return false;
			}
			temp = temp.nextBody;
		}	

		return true;
	}
	
	public void run(){
		BodyPart next = null;
		while(!EXIT){
			//Game Over ?
			if(!isValidMove(dir)){
				create();  
			}
			
			//catched the food?
			if(head.x==food.x && head.y==food.y){
				food.generate();
				this.setSpeed(this.SPEED-30);
			}else{
				BodyPart temp=head;
				while(temp.nextBody.nextBody!=null){
					temp = temp.nextBody;
				}
				temp.nextBody=null;
			}
			
			switch(dir){
			case    UP:
				next = new BodyPart(head.x-1,head.y);
				break;
			case RIGHT:
				next = new BodyPart(head.x,head.y+1);
				break;
			case  DOWN:
				next = new BodyPart(head.x+1,head.y);
					break;
			case  LEFT:
				next = new BodyPart(head.x,head.y-1);
				break;
			}
			
			next.nextBody = head;
			head = next;		
		
			//Speed of Snake :)
			try {
				sleep(SPEED);
			} catch (InterruptedException e) {	}
		}
	}

	public int[][] getArray() {
		int[][] array = GeneralUtils.emptyArray(8,8);
		BodyPart temp = head;
		while(temp!=null){
			array[temp.x][temp.y] = COLOR_SNAKE; //RED
			temp = temp.nextBody;
		}
		array[food.x][food.y] = COLOR_FOOD;
		
		return array;
	}
	
	public void exit(){
		EXIT = true;
	}

	public boolean obstacleAhead(){
		return false;
	}

	public Food getFood() {
		return food;
	}

	public Dir getDir() {
		return dir;
	}
	


	public void setDir(Dir dir) {
		switch(dir){
		case    UP:
			if (!this.dir.equals(Dir.DOWN)) {
				this.dir = dir;
			}
			break;
		case RIGHT:
			if (!this.dir.equals(Dir.LEFT)) {
				this.dir = dir;
			}
			break;
		case  DOWN:
			if (!this.dir.equals(Dir.UP)) {
				this.dir = dir;
			}
			break;
		case  LEFT:
			if (!this.dir.equals(Dir.RIGHT)) {
				this.dir = dir;
			}
			break;
		}
	}
	
	public void setSpeed(int speed){
		this.SPEED = speed;
	}
}
