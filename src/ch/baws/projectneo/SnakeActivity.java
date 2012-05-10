package ch.baws.projectneo;

import ch.baws.projectneo.effects.SnakePlayer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SnakeActivity extends Activity implements OnClickListener{

	private ProjectMORPHEUS application;
	
	SnakePlayer effect;
	
	Button up,down,right,left;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eff_snake);
		
		up = (Button) findViewById(R.id.snake_up);
		down = (Button) findViewById(R.id.snake_down);
		left = (Button) findViewById(R.id.snake_left);
		right = (Button) findViewById(R.id.snake_right);
		
		up.setOnClickListener(this);
		down.setOnClickListener(this);
		left.setOnClickListener(this);
		right.setOnClickListener(this);
		
		setContentView(R.layout.eff_snake);
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
		effect = new SnakePlayer();
		application.setEffect(effect);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.snake_up:
			//TODO What happens if user clicks UP?
			break;
		case R.id.snake_down:
			//TODO What happens if user clicks DOWN?
			break;
		case R.id.snake_left:
			//TODO What happens if user clicks LEFT?
			break;
		case R.id.snake_right:
			//TODO What happens if user clicks RIGHT?
			break;
		}
		
	}

}
