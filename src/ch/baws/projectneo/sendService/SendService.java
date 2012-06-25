package ch.baws.projectneo.sendService;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ch.baws.projectneo.BluetoothUtils;
import ch.baws.projectneo.ProjectMORPHEUS;
import ch.baws.projectneo.R;
import ch.baws.projectneo.TrinityActivity;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
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
	
	
	// Notifier stuffs
	NotificationManager mNotificationManager;
	private static final int NOTIFICATION_ID = 1;
	
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
		
		// notifier
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flag,int startId) {
		super.onStartCommand(intent, flag, startId);
		
		if (D)		Log.d(TAG, "onStartCommand Service");
		

		
		//---- Notification
		int icon = R.drawable.ic_app;
		CharSequence tickerText = "connecting...";
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);
		
		Context context = getApplicationContext();
		CharSequence contentTitle = "ProjectNEO";
		CharSequence contentText = "Service is running...";
		Intent notificationIntent = new Intent(this, TrinityActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		notification.ledARGB = 0xffffffff;
		notification.ledOnMS = 100;
		notification.ledOffMS = 100;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		
		notification.flags |= Notification.FLAG_NO_CLEAR + Notification.FLAG_ONGOING_EVENT;
		
		mNotificationManager.notify(NOTIFICATION_ID, notification);

		if(!runFlag){
			runFlag = true;
			
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
			application.setServiceRunning(true);
		}
		
		return Service.START_STICKY;
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
		mNotificationManager.cancel(NOTIFICATION_ID);
	}
	
//	public boolean isRunning(){
//	    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//	        if ("com.example.MyService".equals(service.service.getClassName())) {
//	            return true;
//	        }
//	    }
//	    return false;
//	}


	
}
