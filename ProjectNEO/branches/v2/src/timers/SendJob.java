package timers;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.util.Log;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.effects.Effect;
/**
 * SendJob implements all the stuff that is needed to send constant data over bluetooth,
 * no need for Timers & co.
 * see EffectActivity for usage
 * 
 * @author Thomas Richner
 *
 */
public class SendJob {
	
	private static final String TAG = "SEND_JOB";
	private static final boolean D = true;
	private final int FPS = 20;
	
	private ScheduledThreadPoolExecutor executor;
	private SendTimer sendTimer;
	private BluetoothUtils bluetooth;
	
	public SendJob(BluetoothUtils bluetooth){
		if (D)		Log.e(TAG, "initialise SendJob");
		this.bluetooth = bluetooth;
		executor = new ScheduledThreadPoolExecutor(1);
		sendTimer = new SendTimer(bluetooth);
	}
	
	public SendJob(Effect effect, BluetoothUtils bluetooth){
		if (D)		Log.e(TAG, "initialise SendJob");
		this.bluetooth = bluetooth;
		executor = new ScheduledThreadPoolExecutor(1);
		sendTimer = new SendTimer(effect, bluetooth);
	}
	
	public void setEffect(Effect effect){
		if (D)		Log.e(TAG, "change effect");
		sendTimer.setEffect(effect);
	}
	
	public void start(){
		if (D)		Log.e(TAG, "starting the SendJob");
		bluetooth.connect();
		executor.scheduleAtFixedRate(sendTimer, 0, 1000/FPS, TimeUnit.MILLISECONDS);
	}

	public void stop(){
		if (D)		Log.e(TAG, "stopping the SendJob");
		executor.shutdownNow();
		sendTimer.stopEffect();
		bluetooth.close();
	}
}
