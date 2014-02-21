package com.graeme.workoutscheduler;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RemoveWorkout extends Activity {
	Workout workout = new Workout();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_workout);
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			workout = (Workout) bundle.getSerializable("workout");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_workout, menu);
		return true;
	}
	public void removeWorkout(View v){
		Intent removeWorkoutIntent = new Intent(this, Homepage.class);
		File removedFile = new File(getFilesDir() + "/" + workout.getWorkoutName());
		removedFile.delete();
		startActivity(removeWorkoutIntent);
	}
	public void keepWorkout(View v){
		Intent keepWorkoutIntent = new Intent(this, EditWorkout.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		keepWorkoutIntent.putExtras(bundle);
		startActivity(keepWorkoutIntent);
	}

}
