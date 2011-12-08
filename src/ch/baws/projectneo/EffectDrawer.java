package ch.baws.projectneo;


import android.util.Log;
import android.widget.TextView;

public class EffectDrawer {
	
	
	private static final String TAG = "EFFECTDRAWER";
	private static final boolean D = true;
	private TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7;
	
	
	public  EffectDrawer(TextView[] textview)
	{
		this.tv0 = textview[0];
		this.tv1 = textview[1];
		this.tv2 = textview[2];
		this.tv3 = textview[3];
		this.tv4 = textview[4];
		this.tv5 = textview[5];
		this.tv6 = textview[6];
		this.tv7 = textview[7];
	
	}
	
	public void draw(int[][] array){
		if (this!=null){
	   		if (D) Log.e(TAG, "Start drawing");
	   		tv0.setText("huhu");
			//setTextHelper(array, 0, tv0);
			setTextHelper(array, 1, tv1);
			setTextHelper(array, 2, tv2);
			setTextHelper(array, 3, tv3);
			setTextHelper(array, 4, tv4);
			setTextHelper(array, 5, tv5);
			setTextHelper(array, 6, tv6);
			setTextHelper(array, 7, tv7);				
		}
	}
	
	private void setTextHelper(int[][] array, int pos, TextView tv){
		String arr = new String();
		arr = "";
		for(int i=0;i<8;i++){ // first textview
	        switch (array[pos][i]) {
	        	case 0:	
	        		arr+=("O ");
	        	case 1:		        	
	        		arr+=("R ");

	        	case 2:		        	
	        		arr+=("G ");
	
	        	case 3:		        	
	        		arr+=("B ");

	        	
	        }
		}
		tv.setText(arr);
	}

}
