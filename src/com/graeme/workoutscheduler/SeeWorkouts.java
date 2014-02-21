package com.graeme.workoutscheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import com.graeme.workoutscheduler.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SeeWorkouts extends Activity {
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_workouts);
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Workout workout = null;
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
				workout = (Workout) ois.readObject();
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
			String workoutName = workout.getWorkoutName();
			String workoutDescription = workout.getWorkoutDescription();
			workoutNames.add(workoutName + "\n"+workoutDescription);
		}
		
		listView = (ListView) findViewById(R.id.list);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, workoutNames);
         listView.setAdapter(adapter); 
		 listView.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
          int itemPosition     = position;
          String  itemValue    = (String) listView.getItemAtPosition(position);
           Toast.makeText(getApplicationContext(),
             "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
             .show();
         }
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.see_workouts, menu);
		return true;
	}

}
