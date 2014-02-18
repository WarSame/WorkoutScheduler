package com.graeme.workoutscheduler;

import com.graeme.workoutscheduler.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class FollowWorkout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_follow_workout);
		Workout workout = new Workout();
		TextView edit = (TextView)findViewById(R.id.textView1);
		String nameString = workout.getWorkoutName();
		edit.setText(nameString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.follow_workout, menu);
		return true;
	}

}
