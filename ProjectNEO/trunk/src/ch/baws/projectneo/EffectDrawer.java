package ch.baws.projectneo;


import android.util.Log;
public class EffectDrawer {
	
	
	private static final String TAG = "EFFECTDRAWER";
	private static final boolean D = true;
	private EffectActivity ea;
	
	
	public  EffectDrawer(EffectActivity in_ea)		
	{
		this.ea = in_ea;
	}
	
	
	public void draw(int[][] array){
		String str = new String();
		str = "";
		int pos=0;
		int i=0;
		if (D) Log.e(TAG, "Start drawing");
		GeneralUtils.drawArray(array,8,8);
		for(i=0;pos<8;pos++)
		{
			for(i=0;i<8;i++){ // first textview
				switch (array[pos][i]) {
					case 0:	
						str+=("O ");
					case 1:		        	
						str+=("R ");

					case 2:		        	
						str+=("G ");
	
					case 3:		        	
						str+=("B ");
				}

	        	
	        }
			ea.changeText(str, pos);	
		}
		
		
	}

}
