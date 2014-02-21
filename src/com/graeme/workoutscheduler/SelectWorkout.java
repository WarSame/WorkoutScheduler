package com.graeme.workoutscheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
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

public class SelectWorkout extends Activity {

	ArrayList<Workout> workoutList = new ArrayList<Workout>();
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_workout);

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File[] files = getFilesDir().listFiles();
		ArrayList<String> workoutNames = new ArrayList<String>();		
		for (int i = 0; i <files.length; i++){
			try {
				fis = new FileInputStream(getFilesDir()+"/"+files[i].getName());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ois = new ObjectInputStream(fis);
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				workoutList.add((Workout) ois.readObject());
				fis.close();
				ois.close();
			} catch (OptionalDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String workoutName = workoutList.get(i).getWorkoutName();
			String workoutDescription = workoutList.get(i).getWorkoutDescription();
			workoutNames.add(workoutName + "\n"+workoutDescription);
		}
		
		listView = (ListView) findViewById(R.id.list);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, workoutNames);
         listView.setAdapter(adapter); 
		 listView.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
        	 //On item click, use this as a workout to be edited
          // ListView Clicked item index
          int itemPosition     = position;
     	 Workout workout = workoutList.get(itemPosition);
     	 Intent editWorkoutIntent = new Intent(SelectWorkout.this, EditWorkout.class);
 		Bundle bundle = new Bundle();
 		bundle.putSerializable("workout", workout);
 		editWorkoutIntent.putExtra("type", "existing");
 		editWorkoutIntent.putExtras(bundle);
 		startActivity(editWorkoutIntent);
         }
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_workout, menu);
		return true;
		
	}
	public void newWorkout(View v){
		Intent newWorkoutIntent = new Intent(this, EditWorkout.class);
		newWorkoutIntent.putExtra("type", "new");
		startActivity(newWorkoutIntent);
	}
	public void goBack(View v){
		Intent goBackIntent = new Intent(this, Homepage.class);
		startActivity(goBackIntent);
	}

}
