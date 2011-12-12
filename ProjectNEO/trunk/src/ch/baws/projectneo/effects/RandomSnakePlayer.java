package ch.baws.projectneo.effects;

import java.util.Random;

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
			whereToGo = rand.nextInt(3);
			switch(whereToGo){
			case 0:
				if(snake.isValidMove(snake.getDir())) break;
			case 1:
				snake.turnLeft();
				if(snake.isValidMove(snake.getDir())){
					break;
				}else{
					snake.turnRight();
					if(snake.isValidMove(snake.getDir()))
						break;
				}
			case 2:
				snake.turnRight();
				if(snake.isValidMove(snake.getDir())){
					break;
				}else{
					snake.turnLeft();
					if(snake.isValidMove(snake.getDir()))
						break;
				}
				snake.turnLeft();
				break;
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
		snake.exit();
	}
}
