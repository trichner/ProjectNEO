package ch.baws.projectneo.effects;

import android.media.audiofx.Visualizer;
import android.util.Log;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
import ch.baws.projectneo.frameGenerator.Frame;

public class AudioVisualizer extends Effect {
	
	private static final String TAG = "AudioVisualizer";
	private static final boolean D = true;	
	
	Visualizer visualizer;
	int[][] array;
	
	public AudioVisualizer(){
		super("ThomasR", "Audio Visualizer");
		this.icon = R.drawable.ic_app;
		
		if(D) Log.d(TAG,"New Visualizer");
		
		array = GeneralUtils.emptyArray(8, 8);
		visualizer = new Visualizer(0);  // use audio output mix
		if(D) Log.d(TAG,"Max capture size: " + Integer.toString(Visualizer.getMaxCaptureRate()));
		visualizer.setCaptureSize(1024);
	}
	
	@Override
	public int[][] getArray() {
		//this.interrupt();
		return array;
	}

	@Override
	public void run() {
		int err,MAX,MIN;
		byte[] buffer_fft = new byte[visualizer.getCaptureSize()];
		int[] magnitude = new int[8];
		visualizer.setEnabled(true);
		while(!EXIT){
			
			err = visualizer.getFft(buffer_fft);
			MAX=0; MIN=Integer.MAX_VALUE;
			if(err!=Visualizer.SUCCESS){
				Log.e(TAG,"FFT wen't wrong, errorcode: " + err);
				for(int i=0;i<8;i++){	// init the array anyway
					magnitude[i]=0;
				}
			}else{
				if(D) Log.d(TAG,"FFT success");
				int real,imag;
				for(int j=0;j<8;j++){
					double mag=0;
					for(int i=0; i<visualizer.getCaptureSize()/8;i+=2){			//add up a whole band
						real = buffer_fft[j*visualizer.getCaptureSize()/8+i];	//real part of a frequency
						imag = buffer_fft[j*visualizer.getCaptureSize()/8+i+1]; //imaginary part of a frequency
						mag += Math.sqrt(real*real+imag*imag);					//only use the absolute value, phase is not interesting
					}
					magnitude[j] = (int) (10 * Math.log10(mag)); // use dB value
					
					MAX = Math.max(magnitude[j],MAX);
					
					MIN = Math.min(magnitude[j],MIN);
					
					if(D) Log.d(TAG, "MAX"+j+": "+MAX);
					if(D) Log.d(TAG, "MIN"+j+": "+MIN);
				}
				if(D) Log.d(TAG, "magnitude="+magnitude[0]+ ","+magnitude[1]+ ","+magnitude[2]+ ","+magnitude[3]+ ","+magnitude[4]+ ","+magnitude[5]+ ","+magnitude[6]+ ","+magnitude[7]);
				if(D) Log.d(TAG, "MIN: "+MIN);
				if(D) Log.d(TAG, "MAX: "+MAX);
			}
			
			for(int i=0;i<8;i++){
				if(magnitude[i]>1/8*(MAX-MIN)+MIN){
					array[0][i] = Frame.NEO_GREEN;
				}
				if(magnitude[i]>2/8*(MAX-MIN)+MIN){
					array[1][i] = Frame.NEO_GREEN;
				}
				if(magnitude[i]>3/8*(MAX-MIN)+MIN){
					array[2][i] = Frame.NEO_GREEN;
				}
				if(magnitude[i]>4/8*(MAX-MIN)+MIN){
					array[3][i] = Frame.NEO_YELLOW;
				}
				if(magnitude[i]>5/8*(MAX-MIN)+MIN){
					array[4][i] = Frame.NEO_YELLOW;
				}
				if(magnitude[i]>6/8*(MAX-MIN)+MIN){
					array[5][i] = Frame.NEO_RED;
				}
				if(magnitude[i]>7/8*(MAX-MIN)+MIN){
					array[6][i] = Frame.NEO_RED;
				}
				if(magnitude[i]==MAX){
					array[7][i] = Frame.NEO_WHITE;
				}	
			}
			
			try {
				sleep(1000/30);
			} catch (InterruptedException e) {}
		}
		visualizer.release();
	}

}
