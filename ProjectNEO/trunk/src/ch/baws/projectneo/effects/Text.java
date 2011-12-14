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
	
	//Declare Arrays
	private List<Integer> hexArray = new ArrayList<Integer>();
	private List<int[]> clrArray = new ArrayList<int[]>();
	
	//Define Layout
	int TEXT = RED;
	int BACK = BLU;
	
	//Define Speed
	static final int SLO = 500;
	static final int FST = 100;
	int speed = SLO;
	
	//Define Lines for letters
	//final int[] a00000 = {BACK, BACK, BACK, BACK, BACK};
//	final int[] a00001 = {BACK, BACK, BACK, BACK, TEXT};
//	final int[] a00010 = {BACK, BACK, BACK, TEXT, BACK};
//	final int[] a00100 = {BACK, BACK, TEXT, BACK, BACK};
//	final int[] a01000 = {BACK, TEXT, BACK, BACK, BACK};
//	final int[] a01010 = {BACK, TEXT, BACK, TEXT, BACK};
//	final int[] a01110 = {BACK, TEXT, TEXT, TEXT, BACK};
//	final int[] a01111 = {BACK, TEXT, TEXT, TEXT, TEXT};
//	final int[] a10000 = {TEXT, BACK, BACK, BACK, BACK};
//	final int[] a10001 = {TEXT, BACK, BACK, BACK, TEXT};
//	final int[] a10010 = {TEXT, BACK, BACK, TEXT, BACK};
//	final int[] a10011 = {TEXT, BACK, BACK, TEXT, TEXT};
//	final int[] a10100 = {TEXT, BACK, TEXT, BACK, BACK};
//	final int[] a10101 = {TEXT, BACK, TEXT, BACK, TEXT};
//	final int[] a11000 = {TEXT, TEXT, BACK, BACK, BACK};
//	final int[] a11001 = {TEXT, TEXT, BACK, BACK, TEXT};
//	final int[] a11011 = {TEXT, TEXT, BACK, TEXT, TEXT};
//	final int[] a11100 = {TEXT, TEXT, TEXT, BACK, BACK};
//	final int[] a11110 = {TEXT, TEXT, TEXT, TEXT, BACK};
//	final int[] a11111 = {TEXT, TEXT, TEXT, TEXT, TEXT};
	
	
	
	//Define Letters for Output
	
	final int[] A = {0x7f, 0x90, 0x90, 0x90, 0x7f};
	final int[] B = {0xff, 0x91, 0x91, 0x91, 0x6e};
	final int[] C = {0x7e, 0x81, 0x81, 0x81, 0x81};
	final int[] D = {0xff, 0x81, 0x81, 0x81, 0x7e};
	final int[] E = {0xff, 0x91, 0x91, 0x81, 0x81};
	final int[] F = {0xff, 0x90, 0x90, 0x80, 0x80};
	final int[] G = {0x7e, 0x81, 0x81, 0x90, 0x9f};
	final int[] H = {0xff, 0x10, 0x10, 0x10, 0xff};
//	final int[][] C = {a01111, a10000, a10000, a10000, a10000, a10000, a10000, a01111};
//	final int[][] D = {a11110, a10001, a10001, a10001, a10001, a10001, a10001, a11110};
//	final int[][] E = {a11111, a10000, a10000, a11100, a10000, a10000, a10000, a11111};
//	final int[][] F = {a11111, a10000, a10000, a11100, a10000, a10000, a10000, a10000};
//	final int[][] G = {a01111, a10000, a10000, a10011, a10001, a10001, a10001, a01110};
//	final int[][] H = {a10001, a10001, a10001, a11111, a10001, a10001, a10001, a10001};
//	final int[][] I = {a11111, a00100, a00100, a00100, a00100, a00100, a00100, a11111};
//	final int[][] J = {a00001, a00001, a00001, a00001, a00001, a00001, a10001, a01110};
//	final int[][] K = {a10001, a10010, a10100, a11000, a10100, a10010, a10001, a10001};
//	final int[][] L = {a10000, a10000, a10000, a10000, a10000, a10000, a10000, a11111};
//	final int[][] M = {a10001, a11011, a10101, a10101, a10001, a10001, a10001, a10001};
//	final int[][] N = {a10001, a11001, a10101, a10101, a10101, a10011, a10001, a10001};
//	final int[][] O = {a01110, a10001, a10001, a10001, a10001, a10001, a10001, a01110};
//	final int[][] P = {a11110, a10001, a10001, a11110, a10000, a10000, a10000, a10000};
//	final int[][] Q = {a01110, a10001, a10001, a10001, a10001, a10001, a10011, a01111};
//	final int[][] R = {a11110, a10001, a10001, a11110, a10010, a10001, a10001, a10001};
//	final int[][] S = {a01111, a10000, a10000, a01110, a00001, a00001, a00001, a11110};
//	final int[][] T = {a11111, a00100, a00100, a00100, a00100, a00100, a00100, a00100};
//	final int[][] U = {a10001, a10001, a10001, a10001, a10001, a10001, a10001, a01110};
//	final int[][] V = {a10001, a10001, a10001, a10001, a01010, a01010, a00100, a00100};
//	final int[][] W = {a10001, a10001, a10001, a10001, a10101, a01110, a01010, a01010};
//	final int[][] X = {a10001, a10001, a01010, a00100, a01010, a10001, a10001, a10001};
//	final int[][] Y = {a10001, a10001, a01010, a00100, a00100, a00100, a00100, a00100};
//	final int[][] Z = {a11111, a00001, a00010, a00100, a01000, a10000, a10000, a11111};
	

	
	//Define Letter Array
	final int[][] letters = {A,B,C,D,E,F,G,H};
	
	
	@Override
	public void run() {
		
		/*
		 * Array format: array[line][column]
		 * HexArray format: hexArray[hexcolumn]
		 */
		setTextArray(gettext());
		
		while (!EXIT) 
		{
			//create array from color array
			for (int i=0; i<clrArray.size()-8; i++)
			{
				for(int col=0; col<8; col++)
					for(int line=0; line<8; line++)
						array[line][col] = clrArray.get(col+i)[line];
			
				//wait for next step
				try {
					sleep(speed);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	
	private String gettext() {
		
		return "abcdefgh";
	}

	private void setTextArray(String text) {
		//First screen is empty
		for (int i=0;i<8;i++) {
			hexArray.add(0);
		}
    	
    	//fill in the Letters
   	
    	text = text.toUpperCase();
    	
    	for (int i=0; i<text.length(); i++)
    	{
    		for (int j=0; j<5; j++)
    		{
    			hexArray.add(letters[(int)text.charAt(i)-65][j]);
    		}
    		//add space
    		hexArray.add(0);    		
    	}
    	
    	//create color array
    	for (int col=0; col<hexArray.size(); col++)
    	{
    		//save column here
    		int[] tempcol = new int[8];
    		
    		for (int line=0;line<8;line++)
    		{
    			int mask = (0x80 >> line);
    			if ((hexArray.get(col) & mask) != 0)
    				tempcol[line] = TEXT;
    			else
    				tempcol[line] = BACK;
    		}
    		//write saved col into array
    		clrArray.add(tempcol);
    	}
	}
	
}
