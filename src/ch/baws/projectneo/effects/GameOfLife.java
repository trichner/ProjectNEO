package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.GeneralUtils;

public class GameOfLife extends Effect {
	
	public GameOfLife(){
		array = GeneralUtils.randomArray(8, 8);
	}
	
	
	@Override
	public int[][] getArray() {
		return array;
	}

	@Override
	public void run() {
		int[][] newArr = new int[8][8];
		Random rand = new Random();
		while(!EXIT){
			
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					int count = neighbours(i,j);
					if(array[i][j]==0){
						if(count==3){
							newArr[i][j] = rand.nextInt(6)+1; 
						}else{
							newArr[i][j] = 0;
						}
					}else{
						if(count==2 || count==3){
							newArr[i][j] = rand.nextInt(6)+1;
						}else{
							newArr[i][j] = 0;
						}
					}
				}
				
			}
			
			array = newArr;
			
			try {
				sleep(300);
			} catch (InterruptedException e) {}
		}
		
	}
	
	private int neighbours(int x,int y){
		int count  = 0;
		//x=8;
		//y=8;
		if (x+1 >= 8){
 
			if (y-1 < 0)count= count + array[x-8+1][y+8-1];
			else count= count + array[x-8+1][y-1];
 
			if (y+1 >= 8) count= count + array[x-8+1][y-8+1];
			else count= count + array[x-8+1][y+1];
 
			count= count + array[x-8+1][y];
 
		}
		else{
 
			if (y-1 < 0) count= count + array[x+1][y+8-1];
			else count= count + array[x+1][y-1];
 
			if (y+1 >= 8) count= count + array[x+1][y-8+1];
			else count= count + array[x+1][y+1];
 
			count= count + array[x+1][y];
 
		}
		if (x-1 < 0){
 
			if (y-1 < 0) count= count + array[x+8-1][y+8-1];
			else count= count + array[x+8-1][y-1];
 
			if (y+1 >= 8) count= count + array[x+8-1][y-8+1];
			else count= count + array[x+8-1][y+1];
 
			count= count + array[x+8-1][y];
 
		}
		else{
 
			if (y+1 >= 8) count= count + array[x-1][y-8+1];
			else count= count + array[x-1][y+1];
 
			if (y-1 < 0) count= count + array[x-1][y+8-1];
			else count= count + array[x-1][y-1];			
 
			count= count + array[x-1][y];
 
		}
 
		if (y+1 >= 8) count= count + array[x][y-8+1];
		else count= count + array[x][y+1];
 
		if (y-1 < 0) count= count + array[x][y+8-1];
		else count= count + array[x][y-1];
 
		return count;
	}
	
}
