package com.graeme.workoutscheduler;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddExercise extends Activity {
	private Workout workout = new Workout();
	private Exercise exercise = new Exercise();
	private ArrayList<Exercise> exerciseList = workout.getExerciseList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			if (workoutPass != null){
				workout = workoutPass;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_exercise, menu);
		return true;
	}
	
	public void submitExercise(View v){
		Bundle bundle = getIntent().getExtras();
		Intent submitExerciseIntent = new Intent(this, EditWorkout.class);
		if (exerciseList!=null){
			exerciseList.add(exercise);
			workout.setExerciseList(exerciseList);
		}
		bundle.putSerializable("workout", workout);
		submitExerciseIntent.putExtras(bundle);
		startActivity(submitExerciseIntent);
	}
	public void returnEditWorkout(View v){
		Intent returnEditWorkoutIntent = new Intent(this, EditWorkout.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		returnEditWorkoutIntent.putExtras(bundle);
		startActivity(returnEditWorkoutIntent);
	}
	public void addSet(View v){
		EditText setCountText = (EditText) findViewById(R.id.editText3);
		String setCountString = setCountText.getText().toString();
		if (!setCountString.matches("")){
			int setCount = Integer.parseInt(setCountString);
			ArrayList<Integer> exerciseSetCount = exercise.getRepCountList();
			exerciseSetCount.add(setCount);
			setCountText.setText("0");
		}
	}

}
