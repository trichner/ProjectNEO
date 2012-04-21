package ch.baws.projectneo;

import ch.baws.projectneo.TextActivity.ColorSelectedListener;
import ch.baws.projectneo.effects.Colorfield;
import ch.baws.projectneo.effects.Effect;
import ch.baws.projectneo.frameGenerator.Frame;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ColorfieldActivity extends Activity {
	private static final String TAG = "Colorfield_ACTIVITY";
	private static final boolean D = true;
	
	private ProjectMORPHEUS morpheus;
	private Spinner spnr_color;
	private Colorfield effect;
	private static int[] colors={Frame.NEO_OFF,Frame.NEO_RED,Frame.NEO_GREEN,Frame.NEO_BLUE,Frame.NEO_YELLOW,Frame.NEO_TURK,Frame.NEO_PINK,Frame.NEO_WHITE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eff_colorfield);
        morpheus = (ProjectMORPHEUS) getApplication();
        spnr_color = (Spinner) findViewById(R.id.spnr_color);

		
		
        
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_color.setAdapter(ad); 
        spnr_color.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> av, View v,int position, long id) {
					if (D) Log.d(TAG, "ITEM Clicked Position: " + position);
						//if(effect!=null) 
					effect.setColor(colors[position]);
				
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
        	});
        
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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (D) Log.d(TAG, "++ ON RESUME ++");
		effect = new Colorfield();
		effect.setColor(Frame.NEO_BLUE);
		morpheus.setEffect(effect);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

}
