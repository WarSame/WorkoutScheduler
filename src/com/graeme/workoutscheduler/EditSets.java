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

public class EditSets extends Activity {
	Exercise exercise = new Exercise();
	Workout workout = new Workout();
	ArrayList<Set> setList;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_sets);
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
		setList = exercise.getRepList();
		ArrayList<String> setStrings = new ArrayList<String>();
		for (int i = 0; i<setList.size(); i++){
			int repCount = setList.get(i).getRepCount();
			int repWeight = setList.get(i).getRepWeight();
			setStrings.add("Rep Count: " + repCount + "\n" + "Rep Weight: " + repWeight);
		}
		
		listView = (ListView) findViewById(R.id.list2);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, setStrings);
        listView.setAdapter(adapter); 
		 listView.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
           int position, long id) {
       	 //On item click, use this as an exercise to be edited
         // ListView Clicked item index
         int itemPosition     = position;
         Intent editExerciseIntent = new Intent(EditSets.this, AddSet.class);
    	 Set set = setList.get(itemPosition);
    	 Bundle bundle = new Bundle();
    	 bundle.putSerializable("set", set);
    	 bundle.putSerializable("exercise", exercise);
    	 bundle.putSerializable("workout", workout);
    	 bundle.putInt("index", itemPosition);
    	 editExerciseIntent.putExtras(bundle);
    	 startActivity(editExerciseIntent);
        }
       }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_sets, menu);
		return true;
	}
	
	public void goBack(View v){
		Intent goBackIntent = new Intent(this, AddExercise.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		bundle.putInt("index", -1);
		goBackIntent.putExtras(bundle);
		startActivity(goBackIntent);
	}
	public void newSet(View v){
		Intent newSetIntent = new Intent(this, AddSet.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("workout", workout);
		bundle.putSerializable("exercise", exercise);
		bundle.putInt("index", -1);
		newSetIntent.putExtras(bundle);
		startActivity(newSetIntent);
	}

}
