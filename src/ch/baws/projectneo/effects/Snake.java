package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.Frame;
import ch.baws.projectneo.GeneralUtils;

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
		BodyPart next = null;
		while(!EXIT){
			//Game Over ?
			if(!isValidMove(dir)){
				create();  
			}
			
			//catched the food?
			if(head.x==food.x && head.y==food.y){
				food.generate();
			}else{
				BodyPart temp=head;
				while(temp.nextBody.nextBody!=null){
					temp = temp.nextBody;
				}
				temp.nextBody=null;
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
				sleep(500);
			} catch (InterruptedException e) {	}
		}
	}

	
	public void turnLeft(){
		switch(dir){
		case    UP:
			dir = Dir.LEFT;
			break;
		case RIGHT:
			dir = Dir.UP;
			break;
		case  DOWN:
			dir = Dir.RIGHT;
				break;
		case  LEFT:
			dir = Dir.DOWN;
			break;
		}
	}
	
	public void turnRight(){
		switch(dir){
		case    UP:
			dir = Dir.RIGHT;
			break;
		case RIGHT:
			dir = Dir.DOWN;
			break;
		case  DOWN:
			dir = Dir.LEFT;
				break;
		case  LEFT:
			dir = Dir.UP;
			break;
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

}
