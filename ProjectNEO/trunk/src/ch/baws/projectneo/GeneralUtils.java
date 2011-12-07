package ch.baws.projectneo;

import java.util.Random;

public class GeneralUtils {
	
	public static String randomCharString()
	{
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return String.valueOf(c);
		
	}
	/**
	 * static method randomArray
	 * creates an array with random entries
	 * @return
	 */
	public static int[][] randomArray(int m,int n)
	{
		Random rand = new Random();
		int[][] array = new int[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				array[i][j] = rand.nextInt(2);
			}
		}	
		return array;
	}
	
	/**
	 * static method emptyArray
	 * creates an array with all entries = zero
	 * @return
	 */
	public static int[][] emptyArray(int m, int n)
	{
		int[][] array = new int[m][n]; // default Array, makes sure it contains zeros
    	for (int i=0;i<m;i++){
    		for (int j=0;j<n;j++){
    			array[i][j] = 0;
    		}
    	}
		return array;
	}
}
