package com.graeme.workoutscheduler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddSet extends Activity {
	Exercise exercise = new Exercise();
	Workout workout = new Workout();
	Set set = new Set();
	ArrayList<Set> exerciseRepList = new ArrayList<Set>();
	int setIndex = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_set);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null){
			Workout workoutPass = (Workout) bundle.getSerializable("workout");
			Exercise exercisePass = (Exercise) bundle.getSerializable("exercise");
			Set setPass = (Set) bundle.getSerializable("set");
			setIndex = (int) bundle.getInt("index");
			if (setPass != null){
				set = setPass;
			}
			if (workoutPass != null){
				workout = workoutPass;
			}
			if (exercisePass!=null){
				exercise = exercisePass;
			}
		}
		EditText repCountText = (EditText) findViewById(R.id.editText1);
		EditText repWeightText = (EditText) findViewById(R.id.editText2);
		
		int repCount = set.getRepCount();
		int repWeight = set.getRepWeight();
		repCountText.setText(""+repCount);
		repWeightText.setText(""+repWeight);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_set, menu);
		return true;
	}
	
	public void goBack(View v){
		Intent goBackIntent = new Intent(this, EditSets.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		goBackIntent.putExtras(bundle);
		startActivity(goBackIntent);
	}
	
	public void submitSet(View v){
		Intent submitSetIntent = new Intent(this, SubmitSet.class);
		Bundle bundle = new Bundle();
		
		EditText repCountText = (EditText) findViewById(R.id.editText1);
		EditText repWeightText = (EditText) findViewById(R.id.editText2);
		
		String repCountString = repCountText.getText().toString();
		String repWeightString = repWeightText.getText().toString();
		
		int repCount = Integer.parseInt(repCountString);
		int repWeight = Integer.parseInt(repWeightString);

		Set set = new Set();
		set.setRepCount(repCount);
		set.setRepWeight(repWeight);
		//Need to deal with overwriting vs. adding a new set.
		//If the incoming setIndex is an actual index, then we replace the existing
		//Value at the index with the new value
		//If it is -1, then we add a new value
		exerciseRepList = exercise.getRepList();
		if (setIndex>=0){
			exerciseRepList.set(setIndex, set);
		}
		else{
			exerciseRepList.add(set);
		}
		exercise.setRepList(exerciseRepList);
		
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		submitSetIntent.putExtras(bundle);
		startActivity(submitSetIntent);
	}

	public void removeSet(View v){
		Intent removeSetIntent = new Intent(this, RemoveSet.class);
		Bundle bundle = new Bundle();
		
		bundle.putInt("index", setIndex);
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		removeSetIntent.putExtras(bundle);
		startActivity(removeSetIntent);
	}
}
