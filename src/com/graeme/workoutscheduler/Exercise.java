package com.graeme.workoutscheduler;

import java.util.ArrayList;

public class Exercise {
	private String exerciseName;
	private String exerciseDescription;
	private ArrayList<Integer> repCountList;
	
	public Exercise(){
		exerciseName = "No Exercise Name";
		exerciseDescription = "No Description Given.";
		repCountList = new ArrayList<Integer>();
	}
	public ArrayList<Integer> getRepCountList() {
		return repCountList;
	}
	public void setRepCountList(ArrayList<Integer> repCountList) {
		this.repCountList = repCountList;
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
