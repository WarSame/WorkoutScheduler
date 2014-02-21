package com.graeme.workoutscheduler;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable{
	private static final long serialVersionUID = 1L;
	private String exerciseName;
	private String exerciseDescription;
	private ArrayList<Integer> repCountList;
	private ArrayList<Integer> repWeightList;
	
	public Exercise(){
		exerciseName = "No Exercise Name";
		exerciseDescription = "No Description Given.";
		repCountList = new ArrayList<Integer>();
		repWeightList = new ArrayList<Integer>();
	}
	public ArrayList<Integer> getRepWeightList() {
		return repWeightList;
	}
	public void setRepWeightList(ArrayList<Integer> repWeightList) {
		this.repWeightList = repWeightList;
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
