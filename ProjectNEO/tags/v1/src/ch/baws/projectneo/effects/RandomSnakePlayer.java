package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.effects.Snake.Dir;

public class RandomSnakePlayer extends Effect{

	private Snake snake = new Snake();
		
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
				sleep(100);
			} catch (InterruptedException e) {}
		}
		snake.exit();
	}
}
