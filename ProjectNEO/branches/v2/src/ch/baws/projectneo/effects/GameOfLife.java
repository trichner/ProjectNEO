package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.GeneralUtils;

public class GameOfLife extends Effect {
	Random rand = new Random();
	
	public static final String author = "ThomasR";
	public static final String title = "Conways Game of Life";
	
	public GameOfLife(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				array[i][j] = rand.nextInt(8);
			}
		}
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
			int alive=0;
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					int count = neighbours(i,j);
					if(array[i][j]==0){
						if(count==3){
							newArr[i][j] = rand.nextInt(6)+1;
							alive++;
						}else{
							newArr[i][j] = 0;
						}
					}else{
						if(count==2 || count==3){
							newArr[i][j] = rand.nextInt(6)+1;
							alive++;
						}else{
							newArr[i][j] = 0;
						}
					}
				}
				
			}
			
			if(alive<10){			
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						array[i][j] = rand.nextInt(8);
					}
				}			
			}else{
				array = newArr;
			}
			
			try {
				sleep(300);
			} catch (InterruptedException e) {}
		}
		
	}
	
	private int neighbours(int x,int y){
		int count  = 0;
		
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if(i==0 && j==0) continue;
				if((x+i)<0 || (x+i)>7) continue;
				if((y+j)<0 || (y+j)>7) continue;
				if(array[x+i][y+j]>0) count++;
			}
		}
 
		return count;
	}
	
}
