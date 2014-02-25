package com.graeme.workoutscheduler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SubmitExercise extends Activity {
	Workout workout = new Workout();
	Exercise exercise = new Exercise();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_exercise);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			Exercise exercisePass = (Exercise) bundle.getSerializable("exercise");
			if (workoutPass != null){
				workout = workoutPass;
			}
			if (exercisePass!=null){
				exercise = exercisePass;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit_exercise, menu);
		return true;
	}
	public void returnHome(View v){
		Intent returnHomeIntent = new Intent(this, EditExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		returnHomeIntent.putExtras(bundle);
		startActivity(returnHomeIntent);
	}

}
