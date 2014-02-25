package com.graeme.workoutscheduler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RemoveExercise extends Activity {
	private Workout workout = new Workout();
	private ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
	private String originalExerciseName ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_exercise);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			originalExerciseName = (String) bundle.getString("original");
			if (workoutPass != null){
				workout = workoutPass;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_exercise, menu);
		return true;
	}
	public void keepExercise(View v){
		//Go back without removing the exercise
		Intent removeExerciseIntent = new Intent(this, EditExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		removeExerciseIntent.putExtras(bundle);
		startActivity(removeExerciseIntent);
	}
	public void removeExercise(View v){
		//Remove the exercise, then go back
		exerciseList = workout.getExerciseList();
		for (int i = 0; i<exerciseList.size();i++){
			if (exerciseList.get(i).getExerciseName().equals(originalExerciseName)){
				exerciseList.remove(i);
			}
		}
		workout.setExerciseList(exerciseList);
		Intent removeExerciseIntent = new Intent(this, EditExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		removeExerciseIntent.putExtras(bundle);
		startActivity(removeExerciseIntent);
	}

}
