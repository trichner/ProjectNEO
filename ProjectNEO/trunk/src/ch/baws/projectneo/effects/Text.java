package ch.baws.projectneo.effects;

import java.util.ArrayList;
import java.util.List;


public class Text extends Effect {

	public Text() {
		super();
	}
	
	@Override
	public int[][] getArray() {
		return array;
	}
	
	//Define Colors
	static final int OFF = 0;
	static final int RED = 1;
	static final int GRN = 2;
	static final int BLU = 3;
	static final int YEL = 4;
	static final int TUR = 5;
	static final int PUR = 6;
	static final int WHT = 7;
	
	//Declare Textarray
	private List<int[]> textArray = new ArrayList<int[]>();
	
	//Define Layout
	int TEXT = RED;
	int BACK = BLU;
	
	//Define Lines for letters
	final int[] a00000 = {BACK, BACK, BACK, BACK, BACK};
	final int[] a00001 = {BACK, BACK, BACK, BACK, TEXT};
	final int[] a00010 = {BACK, BACK, BACK, TEXT, BACK};
	final int[] a00100 = {BACK, BACK, TEXT, BACK, BACK};
	final int[] a01000 = {BACK, TEXT, BACK, BACK, BACK};
	final int[] a01010 = {BACK, TEXT, BACK, TEXT, BACK};
	final int[] a01110 = {BACK, TEXT, TEXT, TEXT, BACK};
	final int[] a01111 = {BACK, TEXT, TEXT, TEXT, TEXT};
	final int[] a10000 = {TEXT, BACK, BACK, BACK, BACK};
	final int[] a10001 = {TEXT, BACK, BACK, BACK, TEXT};
	final int[] a10010 = {TEXT, BACK, BACK, TEXT, BACK};
	final int[] a10011 = {TEXT, BACK, BACK, TEXT, TEXT};
	final int[] a10100 = {TEXT, BACK, TEXT, BACK, BACK};
	final int[] a10101 = {TEXT, BACK, TEXT, BACK, TEXT};
	final int[] a11000 = {TEXT, TEXT, BACK, BACK, BACK};
	final int[] a11001 = {TEXT, TEXT, BACK, BACK, TEXT};
	final int[] a11011 = {TEXT, TEXT, BACK, TEXT, TEXT};
	final int[] a11100 = {TEXT, TEXT, TEXT, BACK, BACK};
	final int[] a11110 = {TEXT, TEXT, TEXT, TEXT, BACK};
	final int[] a11111 = {TEXT, TEXT, TEXT, TEXT, TEXT};
	
	
	
	//Define Letters for Output
	
	final int[][] A = {a01110, a10001, a10001, a11111, a10001, a10001, a10001, a10001};
	final int[][] B = {a11110, a10001, a10001, a11110, a10001, a10001, a10001, a11110};
	final int[][] C = {a01111, a10000, a10000, a10000, a10000, a10000, a10000, a01111};
	final int[][] D = {a11110, a10001, a10001, a10001, a10001, a10001, a10001, a11110};
	final int[][] E = {a11111, a10000, a10000, a11100, a10000, a10000, a10000, a11111};
	final int[][] F = {a11111, a10000, a10000, a11100, a10000, a10000, a10000, a10000};
	final int[][] G = {a01111, a10000, a10000, a10011, a10001, a10001, a10001, a01110};
	final int[][] H = {a10001, a10001, a10001, a11111, a10001, a10001, a10001, a10001};
	final int[][] I = {a11111, a00100, a00100, a00100, a00100, a00100, a00100, a11111};
	final int[][] J = {a00001, a00001, a00001, a00001, a00001, a00001, a10001, a01110};
	final int[][] K = {a10001, a10010, a10100, a11000, a10100, a10010, a10001, a10001};
	final int[][] L = {a10000, a10000, a10000, a10000, a10000, a10000, a10000, a11111};
	final int[][] M = {a10001, a11011, a10101, a10101, a10001, a10001, a10001, a10001};
	final int[][] N = {a10001, a11001, a10101, a10101, a10101, a10011, a10001, a10001};
	final int[][] O = {a01110, a10001, a10001, a10001, a10001, a10001, a10001, a01110};
	final int[][] P = {a11110, a10001, a10001, a11110, a10000, a10000, a10000, a10000};
	final int[][] Q = {a01110, a10001, a10001, a10001, a10001, a10001, a10011, a01111};
	final int[][] R = {a11110, a10001, a10001, a11110, a10010, a10001, a10001, a10001};
	final int[][] S = {a01111, a10000, a10000, a01110, a00001, a00001, a00001, a11110};
	final int[][] T = {a11111, a00100, a00100, a00100, a00100, a00100, a00100, a00100};
	final int[][] U = {a10001, a10001, a10001, a10001, a10001, a10001, a10001, a01110};
	final int[][] V = {a10001, a10001, a10001, a10001, a01010, a01010, a00100, a00100};
	final int[][] W = {a10001, a10001, a10001, a10001, a10101, a01110, a01010, a01010};
	final int[][] X = {a10001, a10001, a01010, a00100, a01010, a10001, a10001, a10001};
	final int[][] Y = {a10001, a10001, a01010, a00100, a00100, a00100, a00100, a00100};
	final int[][] Z = {a11111, a00001, a00010, a00100, a01000, a10000, a10000, a11111};
	

	
	//Define Letter Array
	final int[][][] letters = {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
	

	
	
	
	@Override
	public void run() {
		
		/*
		 * Array format: array[line][column]
		 */
		setTextArray(gettext());
				
		
	}
	
	private String gettext() {
		
		return "Android";
	}

	private void setTextArray(String text) {
		//First screen is empty
    	for (int i=0;i<8;i++){
    		textArray.add(a00000);
    	}
    	
    	//fill in the Letters
   	
    	text.toUpperCase();
    	
    	for (int i=0; i<text.length(); i++)
    	{
    		for (int j=0; j<6; j++)
    		{
    		textArray.add(letters[text.charAt(i)-65][j]);
    		}
    		
    		
    	}
    	
    	
	}
}
