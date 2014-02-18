package com.graeme.workoutscheduler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Homepage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }
    
    public void followWorkout(View v){
    	Intent followWorkoutIntent = new Intent(this, FollowWorkout.class);
    	startActivity(followWorkoutIntent);
    }
    
    public void editWorkout(View v){
    	Intent editWorkoutIntent = new Intent(this, EditWorkout.class);
    	startActivity(editWorkoutIntent);
    }
    
    public void seeWorkouts(View v){
    	Intent seeWorkoutsIntent = new Intent(this, SeeWorkouts.class);
    	startActivity(seeWorkoutsIntent);
    }
    
}
