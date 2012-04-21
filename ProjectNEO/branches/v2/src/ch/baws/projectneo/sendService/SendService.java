package ch.baws.projectneo.sendService;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SendService extends Service {

	private static final String TAG = "SEND_SERVICE";
	private static final boolean D = true;
	private final int FPS = 20;
	
	private ScheduledThreadPoolExecutor executor;
	private SendTimer sendTimer;
	private ReceiveTimer receiveTimer;
	private boolean runFlag = false;
	
	private ProjectMORPHEUS application;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate(){
		if (D)		Log.d(TAG, "onCreate SendJob");
		application = (ProjectMORPHEUS) getApplication();
		executor = new ScheduledThreadPoolExecutor(2);
		//application.setBluetooth(new BluetoothUtils());
		sendTimer = new SendTimer(application);
		receiveTimer = new ReceiveTimer(application);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		runFlag = false;
		application.setServiceRunning(false);
		if (D)		Log.d(TAG, "onDestroy SendJob");
		executor.shutdown();
		application.stopEffect();
		application.bluetoothClose();
	}

	@Override
	public int onStartCommand(Intent intent, int flag,int startId) {
		super.onStartCommand(intent, flag, startId);
		
		if (D)		Log.d(TAG, "onStartCommand Service");
		if(!runFlag){
			runFlag = true;
			((ProjectMORPHEUS) super.getApplication()).setServiceRunning(true);
			if (D)		Log.d(TAG, "Set new bluetooth");
			application.setBluetooth(new BluetoothUtils());
			//if(!application.getBluetooth().active()){
				if (D)		Log.d(TAG, "connect bluetooth");
				application.bluetoothConnect();
				if (D)		Log.d(TAG, "start Effect");
				application.startEffect();
				if (D)		Log.d(TAG, "schedule Jobs");
				executor.scheduleAtFixedRate(sendTimer, 50, 1000/FPS, TimeUnit.MILLISECONDS);
				executor.scheduleAtFixedRate(receiveTimer, 50+500/FPS, 1000/FPS, TimeUnit.MILLISECONDS);
			//}
		}
		return Service.START_STICKY;
	}
	
}
