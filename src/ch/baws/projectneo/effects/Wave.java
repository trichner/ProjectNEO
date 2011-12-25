package ch.baws.projectneo.effects;




public class Wave extends Effect{
	public Wave(){
		super("MarcelM","Wave");
	}
	
	@Override
	public int[][] getArray() {
		return this.array;
	}

	@Override
	public void run() {
		
		
		int line = 0;
		int color = 1;
		
		
		
		while(!EXIT){
			if(line==8)
				line=0;
			if(color==8)
				color=0;
			for (int i=0;i<8;i++)
			{
				this.array[line][i] = color;
			}
			line++;
			if(line%8==0&&line!=0)
				color++;
			//ea.draw(array); //prints the array
			try {
				sleep(250);
			} catch (InterruptedException e) {}
		}
		
	}

}
