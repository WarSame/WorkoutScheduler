package com.graeme.workoutscheduler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EditExercise extends Activity {
	Workout workout = new Workout();
	ArrayList<Exercise> exerciseList;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_exercise);
		ArrayList<String> exerciseNames = new ArrayList<String>();	
		Bundle bundle = getIntent().getExtras();
		workout = (Workout)bundle.getSerializable("workout");
		exerciseList = workout.getExerciseList();
		
		for (int i = 0; i<exerciseList.size(); i++){
			String exerciseName = exerciseList.get(i).getExerciseName();
			String exerciseDescription = exerciseList.get(i).getExerciseDescription();
			exerciseNames.add(exerciseName + "\n" + exerciseDescription);
		}
		
		listView = (ListView) findViewById(R.id.list1);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, exerciseNames);
        listView.setAdapter(adapter); 
		 listView.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
           int position, long id) {
       	 //On item click, use this as an exercise to be edited
         // ListView Clicked item index
         int itemPosition     = position;
         Intent editExerciseIntent = new Intent(EditExercise.this, AddExercise.class);
    	 Exercise exercise = exerciseList.get(itemPosition);
    	 Bundle bundle = new Bundle();
    	 bundle.putSerializable("exercise", exercise);
    	 bundle.putSerializable("workout", workout);
    	 editExerciseIntent.putExtras(bundle);
    	 startActivity(editExerciseIntent);
        }
       }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_exercise, menu);
		return true;
	}
	
	public void newExercise(View v){
		Intent newExerciseIntent = new Intent(this, AddExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		newExerciseIntent.putExtras(bundle);
		startActivity(newExerciseIntent);
	}
	public void goBack(View v){
		Intent goBackIntent = new Intent(this, EditWorkout.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		goBackIntent.putExtras(bundle);
		startActivity(goBackIntent);
	}
	

}
