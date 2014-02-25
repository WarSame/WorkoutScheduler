package com.graeme.workoutscheduler;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable{
	private static final long serialVersionUID = 1L;
	private String exerciseName;
	private String exerciseDescription;
	private ArrayList<Set> repList;
	
	public Exercise(){
		exerciseName = "No Name";
		exerciseDescription = "N/A";
		repList = new ArrayList<Set>();
	}
	public ArrayList<Set> getRepList() {
		return repList;
	}
	public void setRepList(ArrayList<Set> repList) {
		this.repList = repList;
	}
	public Exercise(String name, String description){
		exerciseName = name;
		exerciseDescription = description;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public String getExerciseDescription() {
		return exerciseDescription;
	}
	public void setExerciseDescription(String exerciseDescription) {
		this.exerciseDescription = exerciseDescription;
	}
}
