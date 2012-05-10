package ch.baws.projectneo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import ch.baws.projectneo.effects.*;
import ch.baws.projectneo.sendService.SendService;

public class TrinityActivity extends Activity implements OnClickListener{
	//==== List Adapter
	private class LAdapter extends ArrayAdapter<Class> implements OnClickListener{

		private List<Class> items;

		public LAdapter(Context context, int textViewResourceId, List<Class> effects) {
			super(context, textViewResourceId, effects);
			this.items = effects;
		}

		/**
		 * This gets called whenever a new ListItem is created from the m_orders set.
		 * @param position the position of the listitem at which it is created
		 * @param convertView the View object of the ListItem.
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.item_effect, null);
			}
			//Fetch item
			Class effectClass = items.get(position);
			Effect effect;
			try{
				effect = (Effect) effectClass.newInstance();
				if(D) Log.d(TAG, "Effect instantiated");
				// fill View with info
				//Log.d("QAct","adding TU to list: "+o.toString());
				
				// TextView und ImageView
				TextView title = (TextView) v.findViewById(R.id.item_title);
				TextView description = (TextView) v.findViewById(R.id.item_description);
				
				ImageView icon = (ImageView) v.findViewById(R.id.item_icon);
				icon.setImageResource(effect.getIcon());
				// convert Time Data etc.
				
				title.setText(effect.TITLE);
				description.setText("Author: " + effect.AUTHOR);
				//icon.setImageDrawable(R.drawable);
				
				v.setTag(effectClass);
			} catch (InstantiationException e) {
				Log.e(TAG, "InstantiationException");
			} catch (IllegalAccessException e) {
				if(D) Log.e(TAG, "IllegalAccessException");
			}
//			} catch () {
//				Toast.makeText(getApplicationContext(), "Oh noes! Invalid Effect!", Toast.LENGTH_SHORT).show();
//			}
			return v;
		}

		@Override
		public void onClick(View v) {
//			Class c = (Class) v.getTag();
//			Effect e = null;
//			try {
//				e = (Effect) c.newInstance();
//				application.setEffect(e);
//				Toast.makeText(getApplicationContext(), e.TITLE + " AdapterOnClick", Toast.LENGTH_SHORT).show();
//			} catch (InstantiationException e1) {
//			} catch (IllegalAccessException e1) {
//			}
			
		}
	}
	
	
	 private class StartService extends AsyncTask<Void, Void, Integer> {
		 @Override
	     protected Integer doInBackground(Void... args) {
	         int error_code = 0;
	         
				if(!application.isServiceRunning()){ //service running?
					if(D) Log.d(TAG, "Starting Service...");
					// Bluetooth active?
			    	if(!BluetoothUtils.active()) { // request popup if BT isnt activated
			            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			            startActivityForResult(enableBtIntent, 17);
			        }

					startService(new Intent(getApplicationContext(), SendService.class));
					//tbtn_service.setChecked(true);

				}else{ //stop service
					//if(D) Log.d(TAG, "Stopping Service...");
					if(!application.isServiceRunning()) return 1;
					stopService(new Intent(getApplicationContext(), SendService.class));
					//Toast.makeText(this, "stopping Service...", Toast.LENGTH_SHORT).show();
				}
	         
	         
	         return error_code;
	     }

	     protected void onProgressUpdate() {
	     }

	     protected void onPostExecute(Long result) {
	    	 //Toast.makeText(getApplicationContext(), "Service started.", Toast.LENGTH_SHORT).show();
	     }
	 }
	
	
	
	
	
	//====================================== actual class...
	final static boolean D = true;
	final static String TAG = "TrinityActivity";
	
	private static boolean BT_ON = false;
	
	private ListView effects_list;
	private Button btn_settings;
	private Button btn_about;
	private ToggleButton tbtn_service;
	private ProjectMORPHEUS application;
	
	private LAdapter effects_adapter;
	
	private static List<Class> effects;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   
		setContentView(R.layout.main);
		
		//==== init static stuff
		effects = new ArrayList<Class>();
		
		effects.add(Matrix.class);
		effects.add(Nexus.class);
		effects.add(GameOfLife.class);
		effects.add(RandomSnakePlayer.class);
		effects.add(StarSky.class);
		effects.add(Text.class);
		effects.add(Wave.class);
		effects.add(Buttons.class);
		effects.add(Colorfield.class);
		//effects.add(DefaultEffect.class);
		effects.add(AudioVisualizer.class);
		effects.add(BinaryClock.class);
		effects.add(Tetris.class);
		
		//find all Views
		btn_settings = (Button) findViewById(R.id.btn_settings);
		btn_about = (Button) findViewById(R.id.btn_about);
		tbtn_service = (ToggleButton) findViewById(R.id.tbtn_service);
		application = (ProjectMORPHEUS) getApplication();
		
		//---- load List and its adapter
		effects_list = (ListView) findViewById(R.id.effect_list);
		effects_list.setEmptyView((TextView)findViewById(R.id.no_effects));
		
	
		effects_adapter = new LAdapter(this, R.layout.item_effect, effects);
		
		effects_list.setAdapter(effects_adapter);
		effects_list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id){
				Class c = (Class) v.getTag();
				Effect e = null;
				try {
					e = (Effect) c.newInstance();
					application.setEffect(e);
					Toast.makeText(getApplicationContext(), e.TITLE + " running", Toast.LENGTH_SHORT).show();
					if(e.hasAnActivity()){
						final Intent intent = new Intent(TrinityActivity.this,e.getActivity());
						startActivity(intent);
					}
				} catch (InstantiationException e1) {
				} catch (IllegalAccessException e1) {
				}
			}
		
		});
		
		// Set listeners...
		tbtn_service.setOnClickListener(this);
		btn_about.setOnClickListener(this);
		btn_settings.setOnClickListener(this);
    	
		// Bluetooth active?
    	if (!BluetoothUtils.active()) { // request popup if BT isnt activated
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 17);
        }
	}
	
	@Override
	public void onClick(View v) {
		if(D) Log.d(TAG, "some Button clicked");
		switch(v.getId()){
			case R.id.tbtn_service:
				// Start service
				new StartService().execute(); 
				
				break;
			case R.id.btn_about:
				if(D) Log.d(TAG, "About Button Clicked");
				showDialog(0);
				break;
			case R.id.btn_settings:
				if(D) Log.d(TAG, "Settings Button Clicked");
				showDialog(1);
				break;
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode==17){
			if (resultCode == RESULT_OK) {
			}else{
				if(D) Log.d(TAG, "Result NO, stop service");
            	if(!application.isServiceRunning()) if(D) Log.d(TAG, "Service not even running"); 
            	stopService(new Intent(this, SendService.class));
            	Log.d(TAG, "stopped service"); 
            	application.setServiceRunning(false); // -> onResume misses it the other way
            }
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		tbtn_service.setChecked(application.isServiceRunning());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	//==== Popup
	@Override
    protected Dialog onCreateDialog(int id) {
		if(D) Log.d(TAG, "onCreateDialog");
		switch(id){
		case 0:
			if(D) Log.d(TAG, "About Dialog");
			AlertDialog Credits = new AlertDialog.Builder(this).create();
			Credits.setTitle("ProjectNEO::TRINITY");
			Credits.setMessage(getResources().getText(R.string.about));
			Credits.setIcon(R.drawable.ic_app);
			Credits.setButton("Done", new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) {
					// do nothing.
			   }
			});
			return Credits;
		case 1:
			if(D) Log.d(TAG, "About Dialog");
			AlertDialog BugRep = new AlertDialog.Builder(this).create();
			BugRep.setTitle("ProjectNEO::TRINITY");
			BugRep.setMessage(getResources().getText(R.string.bugreport));
			BugRep.setIcon(R.drawable.ic_app);
			BugRep.setButton("Done", new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) {
					// do nothing.
			   }
			});
			return BugRep;
		}
		if(D) Log.e(TAG, "Unknown Dialog ID!");
		return null;
		
	}
}
