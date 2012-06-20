package ch.baws.projectneo.effects;

import java.util.Random;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.frameGenerator.Frame;

public class RainbowEffect extends Effect {

	private static final int r = Frame.NEO_RED;
	private static final int y = Frame.NEO_YELLOW;
	private static final int g = Frame.NEO_GREEN;
	private static final int t = Frame.NEO_TURK;
	private static final int b = Frame.NEO_BLUE;
	private int[][] static_rainbow = {	{0,0,0,0,0,0,0,0},
										{0,0,r,r,r,r,0,0},
										{0,r,y,y,y,y,r,0},
										{r,y,g,g,g,g,y,r},
										{y,g,t,t,t,t,g,y},
										{g,t,b,b,b,b,t,g},
										{t,b,0,0,0,0,b,t},
										{b,0,0,0,0,0,0,b}};
	
	private final String[] mOptions = {"raining","sunny"};
	private boolean isItRaining = false;
	
	public RainbowEffect(){
		super("Thomas Richner","Rainbow!");
		this.hasOnClickOptions = true;
	}
	
	@Override
	public DialogOptions getOnClickDialogOptions(){
		return new DialogOptions("Wheater?", mOptions);
	}
	
	@Override
	public void setOnClickOption(int pos){
		isItRaining = pos==0;
		this.interrupt();
	}
	
	@Override
	public int[][] getArray() {
		return array;
	}

	@Override
	public void run() {
		boolean[][] rain = new boolean[8][8];
		int[][] tmparray;
		Random rand = new Random();
		while(!EXIT){
			if(isItRaining){
				for(int i=6;i>=0;i--){
					System.arraycopy(rain[i], 0, rain[i+1], 0, 8);
				}
				
				//let it rain!
				for(int n=0;n<8;n++){
					if(rand.nextInt(6)==0)	rain[0][n] = true; //chance to rain
					else					rain[0][n] = false;
				}
				
				//merge rainbow & rain. Note: this is kind of slow... BitBoards?
				tmparray = new int[8][8];
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						if(static_rainbow[i][j]!=0) tmparray[i][j] = static_rainbow[i][j];
						else if(rain[i][j])			tmparray[i][j] = b;
						else 						tmparray[i][j] = 0;
					}
				}
				
				array = tmparray;
				try {
					sleep(250);
				} catch (InterruptedException e) {}
			}else{
				array = static_rainbow;
				try {
					sleep(999999);
				} catch (InterruptedException e) {}
			}

		}
	}

}
