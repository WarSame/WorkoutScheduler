package com.graeme.workoutscheduler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RejectWorkout extends Activity {
	Workout workout = new Workout();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reject_workout);
		Bundle bundle = getIntent().getExtras();
		if(bundle!= null){
			workout = (Workout)bundle.getSerializable("workout");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reject_workout, menu);
		return true;
	}
	public void returnHome(View v){
		Intent returnHomeIntent = new Intent(this, EditWorkout.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		returnHomeIntent.putExtras(bundle);
		startActivity(returnHomeIntent);
	}

}
