package ch.baws.projectneo;

import java.util.Random;

public class GeneralUtils {
	
	static int[][] randomArray()
	{
		Random rand = new Random();
		int[][] arr = new int[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				arr[i][j] = rand.nextInt(2);
			}
		}	
		return arr;
	}
}
