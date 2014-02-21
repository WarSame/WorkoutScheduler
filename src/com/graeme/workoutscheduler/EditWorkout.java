package com.graeme.workoutscheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.graeme.workoutscheduler.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditWorkout extends Activity {
	Workout workout = new Workout();
	EditText nameText;
	EditText descriptionText;
	EditText setRestText;
	EditText exerciseRestText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_workout);
		Bundle bundle = getIntent().getExtras();
		if (bundle.getSerializable("workout") != null){
			workout = (Workout) bundle.getSerializable("workout");
		}
		nameText = (EditText)findViewById(R.id.editText1);
		descriptionText = (EditText) findViewById(R.id.editText2);
		setRestText = (EditText) findViewById(R.id.editText3);
		exerciseRestText = (EditText) findViewById(R.id.editText4);
		nameText.setText(workout.getWorkoutName());
		descriptionText.setText(workout.getWorkoutDescription());
		setRestText.setText(""+workout.getSetRestTime());
		exerciseRestText.setText(""+workout.getExerciseRestTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_workout, menu);
		return true;
	}
	public void editExercise(View v){
		//If the user wants to add an exercise, stuff your workout into an intent, and move
		//Into the exercise editor
		Bundle bundle = getIntent().getExtras();
		if (bundle == null){
			bundle = new Bundle();
		}
		Intent editExerciseIntent = new Intent(this, EditExercise.class);
		workout.setWorkoutName(nameText.getText().toString());
		workout.setWorkoutDescription(descriptionText.getText().toString());
		workout.setSetRestTime(Integer.parseInt(setRestText.getText().toString()));
		workout.setExerciseRestTime(Integer.parseInt(exerciseRestText.getText().toString()));
		bundle.putSerializable("workout", workout);
		editExerciseIntent.putExtras(bundle);
		startActivity(editExerciseIntent);
	}
	public void submitWorkout(View v) throws IOException{
		//If the user wants to submit a workout check for legality/existentiality, then store it
		workout.setWorkoutName(nameText.getText().toString());
		workout.setWorkoutDescription(descriptionText.getText().toString());
		workout.setSetRestTime(Integer.parseInt(setRestText.getText().toString()));
		workout.setExerciseRestTime(Integer.parseInt(exerciseRestText.getText().toString()));
		File fileLocation = new File(getFilesDir()+"/"+workout.getWorkoutName());
		String workoutType = getIntent().getStringExtra("type");
		//Deny them if they choose an existing workout name.
		//If they want to overwrite a file they need to delete it and create a new one
		//Or just edit it.
		if (workoutType.equals("new")&&fileLocation.exists()){
			Intent rejectWorkoutIntent = new Intent(this, RejectWorkout.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("workout", workout);
			rejectWorkoutIntent.putExtras(bundle);
			startActivity(rejectWorkoutIntent);
		} else{
			FileOutputStream fos = new FileOutputStream(fileLocation);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(workout);
			oos.close();
			fos.close();
			Intent submitWorkoutIntent = new Intent(this, SubmitWorkout.class);
			startActivity(submitWorkoutIntent);
		}
	}
	public void removeWorkout(View v){
		//If they want to remove a workout pass all the data and send them on to confirm
		//The removal is handled by the confirm.
		Intent removeWorkoutIntent = new Intent(this, RemoveWorkout.class);
		Bundle bundle = getIntent().getExtras();
		bundle.putSerializable("workout", workout);
		removeWorkoutIntent.putExtras(bundle);
		startActivity(removeWorkoutIntent);
	}
	
	public void goBack(View v){
		Intent goBackIntent = new Intent(this, SelectWorkout.class);
		startActivity(goBackIntent);
	}
}
