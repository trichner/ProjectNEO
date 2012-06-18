package ch.baws.projectneo;

import ch.baws.projectneo.effects.HumanSnakePlayer;
import ch.baws.projectneo.effects.HumanSnakePlayer.Dir;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HumanSnakeActivity extends Activity implements OnClickListener{

	protected static final String TAG = "SNAKEACTIVITY";
	protected static final boolean D = true;
	
	private ProjectMORPHEUS application;
	
	HumanSnakePlayer effect;
	
	Button up,down,right,left;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eff_humansnake);
		
		up = (Button) findViewById(R.id.snake_up);
		down = (Button) findViewById(R.id.snake_down);
		left = (Button) findViewById(R.id.snake_left);
		right = (Button) findViewById(R.id.snake_right);
		
		up.setOnClickListener(this);
		down.setOnClickListener(this);
		left.setOnClickListener(this);
		right.setOnClickListener(this);
		
		application = (ProjectMORPHEUS) getApplication();
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
		effect = new HumanSnakePlayer();
		application.setEffect(effect);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		if(D) Log.d(TAG,"Button clicked!");
		switch(v.getId()){
		case R.id.snake_up:
			//TODO What happens if user clicks UP?
			effect.setDir(Dir.UP);
			if(D) Log.d(TAG,"go up");
			break;
		case R.id.snake_down:
			//TODO What happens if user clicks DOWN?
			effect.setDir(Dir.DOWN);
			if(D) Log.d(TAG,"go down");
			break;
		case R.id.snake_left:
			//TODO What happens if user clicks LEFT?
			effect.setDir(Dir.LEFT);
			if(D) Log.d(TAG,"go left");
			break;
		case R.id.snake_right:
			//TODO What happens if user clicks RIGHT?
			effect.setDir(Dir.RIGHT);
			if(D) Log.d(TAG,"go rigth");
			break;
		}
		
	}

}
