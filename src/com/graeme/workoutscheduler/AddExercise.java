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
	private ArrayList<Integer> exerciseRepCount = exercise.getRepCountList();
	private ArrayList<Integer> exerciseRepWeight = exercise.getRepWeightList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
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
		getMenuInflater().inflate(R.menu.add_exercise, menu);
		return true;
	}
	
	public void submitExercise(View v){
		//If they hit the submit button put the exercise in their workout and return them.
		Bundle bundle = new Bundle();
		Intent submitExerciseIntent = new Intent(this, EditExercise.class);
		exercise.setRepCountList(exerciseRepCount);
		exercise.setRepWeightList(exerciseRepWeight);
		
		EditText exerciseNameText = (EditText) findViewById(R.id.editText1);
		EditText exerciseDescriptionText = (EditText)findViewById(R.id.editText2);
		String exerciseName = exerciseNameText.getText().toString();
		String exerciseDescription = exerciseDescriptionText.getText().toString();
		
		exerciseNameText.setText(exercise.getExerciseName());
		exerciseDescriptionText.setText(exercise.getExerciseDescription());
		exercise.setExerciseName(exerciseName);
		exercise.setExerciseName(exerciseDescription);
		exerciseList = workout.getExerciseList();
		exerciseList.add(exercise);
		workout.setExerciseList(exerciseList);
		
		bundle.putSerializable("workout", workout);
		submitExerciseIntent.putExtras(bundle);
		startActivity(submitExerciseIntent);
	}
	public void returnEditWorkout(View v){
		//If they hit the back button, return without adding anything.
		Intent returnEditWorkoutIntent = new Intent(this, EditExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		returnEditWorkoutIntent.putExtras(bundle);
		startActivity(returnEditWorkoutIntent);
	}
	public void addSet(View v){
		//If they hit the add set button, add that rep count to the list.
		EditText repCountText = (EditText) findViewById(R.id.editText3);
		EditText repWeightText = (EditText) findViewById(R.id.editText4);
		String repCountString = repCountText.getText().toString();
		String repWeightString = repWeightText.getText().toString();
		if (!repCountString.matches("") && !repWeightString.matches("")){
			int setCount = Integer.parseInt(repCountString);
			int setWeight = Integer.parseInt(repWeightString);
			exerciseRepCount = exercise.getRepCountList();
			exerciseRepWeight = exercise.getRepWeightList();
			exerciseRepCount.add(setCount);
			exerciseRepWeight.add(setWeight);
			repCountText.setText("0");
			repWeightText.setText("0");
		}
	}
	public void editSets(View v){
		//If they want to edit the sets for this exercise show them the list and let them change the elements
	}

}
