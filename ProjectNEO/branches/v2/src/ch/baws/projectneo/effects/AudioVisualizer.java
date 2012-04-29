package ch.baws.projectneo.effects;

import android.media.audiofx.Visualizer;
import android.util.Log;

import ch.baws.projectneo.GeneralUtils;
import ch.baws.projectneo.R;
import ch.baws.projectneo.frameGenerator.Frame;

public class AudioVisualizer extends Effect {
	
	private static final String TAG = "AudioVisualizer";
	private static final boolean D = false;	
	
	private static final double maxFrequency = 10000*1000;	//in mHz
	private static final double minFrequency = 50*1000;	
	
	private Visualizer visualizer;
	private int[][] array;
	private int kmax,kmin,range,bandwidth;
	
	
	public AudioVisualizer(){
		super("Thomas Richner", "Audio Visualizer");
		this.icon = R.drawable.ic_eff_audiovisualizer;
		
		if(D) Log.d(TAG,"New Visualizer!");
		
		array = GeneralUtils.getEmpty8x8();
		visualizer = new Visualizer(0);  // use audio output mix
		
		if(D) Log.d(TAG,"Max capture size: " + Integer.toString(Visualizer.getMaxCaptureRate()));
		try{
			visualizer.setCaptureSize(1024);
		}catch(IllegalStateException e){
			if(D) Log.d(TAG,"setCaptureSize: Illegal state!");
		}
		kmax = (int) (maxFrequency/((double) visualizer.getSamplingRate())*visualizer.getCaptureSize());
		kmin = (int) (minFrequency/((double) visualizer.getSamplingRate())*visualizer.getCaptureSize());
		bandwidth = (kmax-kmin)/8;
		
		if(D){
			Log.v(TAG, "kmax: "+kmax);
			Log.v(TAG, "kmin: "+kmin);
			Log.v(TAG, "FS: " + visualizer.getSamplingRate());
		}
	}
	
	@Override
	public int[][] getArray() {
		this.interrupt();
		return array;
	}

	@Override
	public void run() {
		int err,MAX,MIN,tMAX,tMIN,real,imag;
		double averageMAX=30, averageMIN=10;
		double mag;
		byte[] buffer_fft = new byte[visualizer.getCaptureSize()];
		int[] magnitude = new int[8];
		visualizer.setEnabled(true);
		while(!EXIT){
			err = visualizer.getFft(buffer_fft);
			tMAX=0; tMIN=Integer.MAX_VALUE;
			if(err!=Visualizer.SUCCESS){
				Log.e(TAG,"FFT wen't wrong, errorcode: " + err);
				for(int i=0;i<8;i++){	// init the array anyway
					magnitude[i]=0;
				}
			}else{
				if(D) Log.d(TAG,"FFT success");
				
				for(int j=0;j<8;j++){	// calculate the magnitude of each bar as a sum of frequencies
					mag=0;
					for(int i=0; i<bandwidth;i+=2){					// add up a whole band
						real = buffer_fft[kmin+j*bandwidth+i];		// real part of a frequency
						imag = buffer_fft[kmin+j*bandwidth+i+1]; 	// imaginary part of a frequency
						mag += Math.sqrt(real*real+imag*imag);		// only use the absolute value, phase is not interesting
					}
					magnitude[j] = (int) (10 * Math.log10(mag)); 	// use dB value
					
					tMAX = Math.max(magnitude[j],tMAX);				// get the maximum and minimum of the magnitudes
					tMIN = Math.min(magnitude[j],tMIN);				
					
					if(D) Log.v(TAG, "MAX"+j+": "+tMAX);
					if(D) Log.v(TAG, "MIN"+j+": "+tMIN);
				}
				
				//get an average
				if(tMAX-tMIN>5 && tMAX>10 && tMIN>5 && tMAX<50){ 	// magic values...
					averageMIN = (((9*averageMIN+tMIN))/10.0);		// weight it
					averageMAX = (((9*averageMAX+tMAX))/10.0);
				}
				
				MAX = (int) (averageMAX+3); // floor(avg+3), magic value
				MIN = (int) (averageMIN);	// floor(avg)
				
				if(D) Log.d(TAG, "magnitude="+magnitude[0]+ ","+magnitude[1]+ ","+magnitude[2]+ ","+magnitude[3]+ ","+magnitude[4]+ ","+magnitude[5]+ ","+magnitude[6]+ ","+magnitude[7]);	
				if(D) Log.d(TAG, "MIN/MAX: "+MIN+" / "+MAX + "    tMIN/tMAX: "+tMIN+" / "+tMAX);
				
				array = GeneralUtils.getEmpty8x8();
				for(int i=0;i<8;i++){
					int tempMag = magnitude[7-i];
					if(tempMag>0.125*(MAX-MIN)+MIN){
						array[0][i] = Frame.NEO_GREEN;
					}
					if(tempMag>0.25*(MAX-MIN)+MIN){
						array[1][i] = Frame.NEO_GREEN;
					}
					if(tempMag>0.375*(MAX-MIN)+MIN){
						array[2][i] = Frame.NEO_GREEN;
					}
					if(tempMag>0.5*(MAX-MIN)+MIN){
						array[3][i] = Frame.NEO_YELLOW;
					}
					if(tempMag>0.625*(MAX-MIN)+MIN){
						array[4][i] = Frame.NEO_YELLOW;
					}
					if(tempMag>0.75*(MAX-MIN)+MIN){
						array[5][i] = Frame.NEO_RED;
					}
					if(tempMag>0.875*(MAX-MIN)+MIN){
						array[6][i] = Frame.NEO_RED;
					}
					if(tempMag>=MAX){
						array[7][i] = Frame.NEO_WHITE;
					}	
				}
				
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
		visualizer.release();
	}

}
