package ch.baws.projectneo.effects;

import java.util.ArrayList;
import java.util.List;


public class Text extends Effect {
	public static final String author = "StefanM";
	public static final String title = "MarqueeV1";
	public Text(String text, int textclr, int backclr, int spd) {
		
		this.text = text;
		this.TEXT = textclr;
		this.BACK = backclr;
		switch (spd) {
			case 1 : this.speed = FST; break;
			case 2 : this.speed = IMB; break;
			default: this.speed = SLO; break;
		}
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
	
	//Define Textvar
	String text = "abcdefghijklmnopqrstuvwxyz";
	
	
	//Define Speed
	static final int SLO = 500;
	static final int FST = 100;
	static final int IMB = 50;
	
	int speed = SLO;
	
	
	//Define Letters for Hexarray
	
	final int[] A = {0x7f, 0x90, 0x90, 0x90, 0x7f};
	final int[] B = {0xff, 0x91, 0x91, 0x91, 0x6e};
	final int[] C = {0x7e, 0x81, 0x81, 0x81, 0x81};
	final int[] D = {0xff, 0x81, 0x81, 0x81, 0x7e};
	final int[] E = {0xff, 0x91, 0x91, 0x81, 0x81};
	final int[] F = {0xff, 0x90, 0x90, 0x80, 0x80};
	final int[] G = {0x7e, 0x81, 0x81, 0x91, 0x0e};
	final int[] H = {0xff, 0x10, 0x10, 0x10, 0xff};
	final int[] I = {0x81, 0x81, 0xff, 0x81, 0x81};
	final int[] J = {0x8e, 0x81, 0xfe, 0x80, 0x80};
	final int[] K = {0xff, 0x10, 0x28, 0x44, 0x83};
	final int[] L = {0xff, 0x01, 0x01, 0x01, 0x01};
	final int[] M = {0xff, 0x40, 0x30, 0x40, 0xff};
	final int[] N = {0xff, 0x60, 0x10, 0x0C, 0xff};
	final int[] O = {0x7e, 0x81, 0x81, 0x81, 0x7e};
	final int[] P = {0xff, 0x90, 0x90, 0x90, 0x60};
	final int[] Q = {0x7e, 0x81, 0x85, 0x82, 0x7d};
	final int[] R = {0xff, 0x90, 0x98, 0x94, 0x63};
	final int[] S = {0x61, 0x91, 0x91, 0x91, 0x8e};
	final int[] T = {0x80, 0x80, 0xff, 0x80, 0x80};
	final int[] U = {0xfe, 0x01, 0x01, 0x01, 0xfe};
	final int[] V = {0xf0, 0x0c, 0x03, 0x0c, 0xf0};
	final int[] W = {0xfc, 0x03, 0x0c, 0x03, 0xfc};
	final int[] X = {0xc7, 0x28, 0x10, 0x28, 0xc7};
	final int[] Y = {0xc0, 0x20, 0x1f, 0x20, 0xc0};
	final int[] Z = {0x87, 0x89, 0x91, 0xa1, 0xc1};

	
	//Define Letter Array
	final int[][] letters = {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
	
	
	@Override
	public void run() {
		
		/*
		 * Array format: array[line][column]
		 * HexArray format: hexArray[hexcolumn]
		 */
		setTextArray(text);
		
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
    			if (Character.isLetter(text.charAt(i)))
    				hexArray.add(letters[(int)text.charAt(i)-65][j]);
    			else
    				hexArray.add(0);
    		}
    		//add space
    		hexArray.add(0);    		
    	}
    	
    	//add last space
    	for(int i=0; i<8; i++)
    		hexArray.add(0);
    	
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
