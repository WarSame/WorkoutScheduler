package com.graeme.workoutscheduler;

import com.graeme.workoutscheduler.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SeeWorkouts extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_workouts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.see_workouts, menu);
		return true;
	}

}
