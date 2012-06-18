package ch.baws.projectneo.effects;

import java.util.Random;

import android.util.Log;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.frameGenerator.Frame;



public class HumanSnakePlayer extends Effect{
	public static class Snake extends Thread{
		private int SPEED = 400;
		private class BodyPart{ // Represents one Pixel of a Snake
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
		private class Food{ // Represents a Food Pixel
			int x;
			int y;
			/**
			 * Generates a random new Food Pixel
			 */
			public void generate(){
				x = rand.nextInt(8);
				y = rand.nextInt(8);
				BodyPart temp=head;
				while(temp.nextBody != null){ // Test if the food would spawn inside the snake
					if(temp.x==x && temp.y == y){
						generate();
						temp = head;
					}else{
						temp = temp.nextBody;
					}
				}
			}
		}
		
		private static final boolean D = true; // Debug Flag
		private static final String TAG = "SNAKE_THREAD";
		
		private static final int COLOR_SNAKE = Frame.NEO_RED;  //Color representing the Snake
		private static final int COLOR_FOOD  = Frame.NEO_BLUE; //Color for the Food piece
		
		public static enum Dir{RIGHT,LEFT,UP,DOWN};
		
		private boolean GAMEOVER = false;
		private BodyPart head;
		private Food food;
		
		private int score;
		private Random rand;
		private int[][] array;
		
		private Dir dir; /** Current direction to go */
		
		public Snake(){
			rand = new Random();
			create();
		}
		
		private void create(){
			head=new BodyPart(4, 4);
			dir = Dir.DOWN;
			score =0;
			BodyPart temp1,temp2;
			temp2 = head;
			for(int i=0;i<3;i++){ // Initialise a new snake in the middle
				temp1 = new BodyPart(head.x+1,head.y);
				temp2.nextBody = temp1;
				temp2 = temp1;
			}
			food = new Food();
			food.generate();
		}
		
		public boolean isValidMove(BodyPart body){
			//Test if OutOfBounds
			if(body.x > 7 || body.x < 0 || body.y >7 || body.y < 0) return false;
			
			//Test if eating itself
			BodyPart temp=head;
			while(temp!=null){
				if(body.equals(temp)){
					return false;
				}
				temp = temp.nextBody;
			}	

			return true;
		}
		
		public void run(){
			BodyPart next = null;
			while(!GAMEOVER){
				
				
				//catched the food?
				if(head.x==food.x && head.y==food.y){
					score++;
					food.generate(); // new food
					if(SPEED>50) this.setSpeed(this.SPEED-30); //increase Speed!
				}else{ // loose the tail pixel
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
				
				//Game Over ?
				if(!isValidMove(head)){
					// fancy Game over effect
					GAMEOVER = true;
					break; // EXIT The loop, GAME OVER!
				}
				
				// Since the snake doesn't change till the next tick, we might as
				// well compute the array once and for all, so the call getArray() doesn't need to do anything
				array = GeneralUtils.getEmpty8x8();
				BodyPart temp = head;
				if(D) Log.d(TAG,"GET ARRAY: starting");
				while(temp!=null){
					if(D) Log.d(TAG,"GET ARRAY: fill 1  temp.x:" + temp.x + " temp.y:" + temp.y);
					array[temp.x][temp.y] = COLOR_SNAKE; //RED
					if(D) Log.d(TAG,"GET ARRAY: fill 2");
					temp = temp.nextBody;
					if(D) Log.d(TAG,"GET ARRAY: fill 3");
				}
				array[food.x][food.y] = COLOR_FOOD;
				if(D) Log.d(TAG,"GET ARRAY: finsh, set food");
				
				//Speed of Snake :)
				try {
					sleep(SPEED);
				} catch (InterruptedException e) {	}
			}
			
		}

		public int[][] getArray(){
			return array;
		}
		
		public void exit(){
			GAMEOVER = true;
			this.interrupt();
		}
		
		public boolean isGameOver(){
			return GAMEOVER;
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
		
		public int getScore(){
			return score;
		}
		
		public void setSpeed(int speed){
			this.SPEED = speed;
		}
		
	}
	
	private Snake snake = new Snake();
	
	public HumanSnakePlayer(){
		super("ThomasR", "SnakeV1");
	}
	@Override
	public int[][] getArray() {
		return snake.getArray();
	}

	@Override
	public void run() {
		Random rand = new Random();
		int whereToGo = 0;
		
		snake.start();
		while(!EXIT){
			boolean quit = false;
			while(!quit){
				whereToGo = rand.nextInt(4);	
				if(snake.isValidMove(snake.getDir())){
					if(whereToGo>1)
						break;
				}
				whereToGo = rand.nextInt(4);
				switch(whereToGo){
				case 0:
					if(snake.isValidMove(Dir.DOWN)&&snake.getDir()!=Dir.UP){
						snake.setDir(Dir.DOWN);
						quit = true;
					}
					break;
				case 1:
					if(snake.isValidMove(Dir.UP)&&snake.getDir()!=Dir.DOWN){
						snake.setDir(Dir.UP);
						quit = true;
					}
					break;
				case 2:
					if(snake.isValidMove(Dir.LEFT)&&snake.getDir()!=Dir.RIGHT){
						snake.setDir(Dir.LEFT);
						quit = true;
					}
					break;
				case 3:
					if(snake.isValidMove(Dir.RIGHT)&&snake.getDir()!=Dir.LEFT){
						snake.setDir(Dir.RIGHT);
						quit = true;
					}
					break;
				}
			}
			
			try {
				sleep(400);
			} catch (InterruptedException e) {}
		}
		snake.exit();
	}
}
