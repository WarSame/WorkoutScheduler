package com.graeme.workoutscheduler;

import com.graeme.workoutscheduler.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditWorkout extends Activity {
	private Workout workout = new Workout();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_workout);
		EditText nameText = (EditText)findViewById(R.id.editText1);
		EditText descriptionText = (EditText) findViewById(R.id.editText2);
		EditText setRestText = (EditText) findViewById(R.id.editText3);
		EditText exerciseRestText = (EditText) findViewById(R.id.editText4);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			if (workoutPass != null){
				workout = workoutPass;
			}
		}
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
	public void addExercise(View v){
		Bundle bundle = getIntent().getExtras();
		if (bundle == null){
			bundle = new Bundle();
		}
		Intent addExerciseIntent = new Intent(this, AddExercise.class);
		EditText nameText = (EditText)findViewById(R.id.editText1);
		EditText descriptionText = (EditText) findViewById(R.id.editText2);
		EditText setRestText = (EditText) findViewById(R.id.editText3);
		EditText exerciseRestText = (EditText) findViewById(R.id.editText4);
		workout.setWorkoutName(nameText.getText().toString());
		workout.setWorkoutDescription(descriptionText.getText().toString());
		workout.setSetRestTime(Integer.parseInt(setRestText.getText().toString()));
		workout.setExerciseRestTime(Integer.parseInt(exerciseRestText.getText().toString()));
		bundle.putSerializable("workout", workout);
		addExerciseIntent.putExtras(bundle);
		startActivity(addExerciseIntent);
	}
	public void submitWorkout(View v){
		Intent submitWorkoutIntent = new Intent(this, SubmitWorkout.class);
		startActivity(submitWorkoutIntent);
	}
}
