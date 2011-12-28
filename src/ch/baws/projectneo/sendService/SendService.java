package ch.baws.projectneo.sendService;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;
import ch.baws.projectneo.effects.Effect;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SendService extends Service {

	private static final String TAG = "SEND_SERVICE";
	private static final boolean D = true;
	private final int FPS = 20;
	
	private ScheduledThreadPoolExecutor executor;
	private SendTimer sendTimer;
	
	private ProjectMORPHEUS application = (ProjectMORPHEUS) getApplication();
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate(){
		if (D)		Log.e(TAG, "initialise SendJob");
		executor = new ScheduledThreadPoolExecutor(1);
		sendTimer = new SendTimer(application);
		application.setBluetooth(new BluetoothUtils());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		executor.shutdown();
		application.getEffect().exit();		//TODO
		application.getBluetooth().close(); //TODO
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		application.getBluetooth().connect(); //TODO
		Effect effect = application.getEffect();
		if(!effect.isAlive()) effect.start();
		executor.scheduleAtFixedRate(sendTimer, 50, 1/FPS, TimeUnit.MILLISECONDS);
	}
	
	
	
	

}
