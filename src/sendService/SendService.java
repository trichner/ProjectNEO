package sendService;

import java.util.concurrent.ScheduledThreadPoolExecutor;

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
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		if (D)		Log.e(TAG, "initialise SendJob");
		executor = new ScheduledThreadPoolExecutor(1);
		sendTimer = new SendTimer((ProjectMORPHEUS) getApplication());
	}
	
	
	
	

}
