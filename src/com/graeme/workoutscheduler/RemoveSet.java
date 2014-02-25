package com.graeme.workoutscheduler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RemoveSet extends Activity {
	Workout workout = new Workout();
	Exercise exercise = new Exercise();
	Bundle bundle = new Bundle();
	int setIndex = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_set);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			Exercise exercisePass = (Exercise) bundle.getSerializable("exercise");
			setIndex = (int) bundle.getInt("index");
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
		getMenuInflater().inflate(R.menu.remove_set, menu);
		return true;
	}
	
	public void removeSet(View v){
		Intent removeSetIntent = new Intent(this, EditSets.class);
		ArrayList<Set> exerciseRepList;
		exerciseRepList = exercise.getRepList();
		if (setIndex>=0){
			exerciseRepList.remove(setIndex);
		}
		exercise.setRepList(exerciseRepList);
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		removeSetIntent.putExtras(bundle);
		startActivity(removeSetIntent);
	}
	public void keepSet(View v){
		Intent keepSetIntent = new Intent(this, EditSets.class);
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		keepSetIntent.putExtras(bundle);
		startActivity(keepSetIntent);
	}

}
