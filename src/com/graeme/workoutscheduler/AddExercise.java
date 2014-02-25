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
	private ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
	String originalExerciseName;
	EditText exerciseNameText;
	EditText exerciseDescriptionText;
	int originalIndex = -1;
	String type = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			Exercise exercisePass = (Exercise) bundle.getSerializable("exercise");
			String typePass = bundle.getString("type");
			originalIndex = bundle.getInt("index");
			if (typePass != null){
				type = typePass;
			}
			if (workoutPass != null){
				workout = workoutPass;
			}
			if (exercisePass!=null){
				exercise = exercisePass;
			}
		}
		exerciseNameText = (EditText) findViewById(R.id.editText1);
		exerciseDescriptionText = (EditText)findViewById(R.id.editText2);
		
		exerciseNameText.setText(exercise.getExerciseName());
		exerciseDescriptionText.setText(exercise.getExerciseDescription());
		originalExerciseName = exercise.getExerciseName();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_exercise, menu);
		return true;
	}
	
	public void removeExercise(View v){		
		Intent removeExerciseIntent = new Intent(this, RemoveExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		bundle.putString("original", originalExerciseName);
		removeExerciseIntent.putExtras(bundle);
		startActivity(removeExerciseIntent);
	}
	
	public void submitExercise(View v){
		//If they hit the submit button put the exercise in their workout and return them.
		Bundle bundle = new Bundle();
		
		EditText exerciseNameText = (EditText) findViewById(R.id.editText1);
		EditText exerciseDescriptionText = (EditText)findViewById(R.id.editText2);
		String exerciseName = exerciseNameText.getText().toString();
		String exerciseDescription = exerciseDescriptionText.getText().toString();
		
		exercise.setExerciseName(exerciseName);
		exercise.setExerciseDescription(exerciseDescription);
		exerciseList = workout.getExerciseList();
		//Check if the exercise is already in this workout's exercise list
		//If it is going to overwrite an old one don't let it be added.
		//If it is overwriting itself let it be added.
		Boolean exerciseExists = false;
		for (int i =0; i<exerciseList.size();i++){
			exerciseName = exerciseList.get(i).getExerciseName();
			if (exerciseName.equals(exercise.getExerciseName())){
				exerciseExists = true;
			}
			if (exerciseName.equals(originalExerciseName)){
				originalIndex = i;
			}
		}
		exerciseName = exercise.getExerciseName();

		Intent submitExerciseIntent = new Intent(this, RejectExercise.class);
		if (exerciseExists){
			//If the location we are moving to is already occupied
			//Check if it is the same as we are moving from
			//If so, we overwrite
			if (originalExerciseName.equals(exerciseName)){
				exerciseList.set(originalIndex, exercise);
				submitExerciseIntent = new Intent(this, SubmitExercise.class);
			}
			//If it is not the same, we do not do anything, to avoid an overwrite
		} else {//If !exerciseExists
			//If the location we are moving to is not occupied
			//Then we occupy it. If our exercise existed beforehand, we remove it
			if (type.equals("existing")){
				exerciseList.remove(originalIndex);
			}
			//Otherwise, we just add the exercise to the list
			exerciseList.add(exercise);
			submitExerciseIntent = new Intent(this, SubmitExercise.class);
		}
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
	public void editSets(View v){
		//If they want to edit the sets for this exercise show them 
		//the list and let them change the elements
		EditText exerciseNameText = (EditText) findViewById(R.id.editText1);
		EditText exerciseDescriptionText = (EditText)findViewById(R.id.editText2);
		
		Intent editSetsIntent = new Intent(this, EditSets.class);
		Bundle bundle = new Bundle();
		exercise.setExerciseDescription(exerciseDescriptionText.getText().toString());
		exercise.setExerciseName(exerciseNameText.getText().toString());
		
		bundle.putSerializable("workout",workout);
		bundle.putSerializable("exercise", exercise);
		editSetsIntent.putExtras(bundle);
		startActivity(editSetsIntent);
	}

}
