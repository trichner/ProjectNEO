package ch.baws.projectneo;

import ch.baws.projectneo.effects.Tetris;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TetrisActivity extends Activity implements OnClickListener{

	Tetris tetris;
	ProjectMORPHEUS application;

	Button left,right,drop,rotate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eff_tetris);
		application = (ProjectMORPHEUS) getApplication();
		
		left = (Button) findViewById(R.id.tetris_left);
		right = (Button) findViewById(R.id.tetris_right);
		drop = (Button) findViewById(R.id.tetris_drop);
		//rotate = (Button) findViewById(R.id.tetris_rotate);
		
		//rotate.setOnClickListener(this);
		left.setOnClickListener(this);
		right.setOnClickListener(this);
		drop.setOnClickListener(this);
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
		tetris = new Tetris();
		application.setEffect(tetris);	
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tetris_right:
			tetris.shiftRight();
			break;
		case R.id.tetris_left:
			tetris.shiftLeft();
			break;
		case R.id.tetris_drop:
			tetris.dropDown();
			break;
//		case R.id.tetris_rotate:
//			tetris.rotate();
//			break;
		}
		
	}

}
