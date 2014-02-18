package com.graeme.workoutscheduler;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workoutName;
	private String workoutDescription;
	private ArrayList<Exercise> exerciseList;
	private int setRestTime;
	private int exerciseRestTime;
	
	public Workout(){
		workoutName = "No Name";
		workoutDescription = "N/A";
		exerciseList = new ArrayList<Exercise>();
		setRestTime = 0;
		exerciseRestTime = 0;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public String getWorkoutDescription() {
		return workoutDescription;
	}
	public void setWorkoutDescription(String workoutDescription) {
		this.workoutDescription = workoutDescription;
	}
	public ArrayList<Exercise> getExerciseList() {
		return exerciseList;
	}
	public void setExerciseList(ArrayList<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}
	public int getSetRestTime() {
		return setRestTime;
	}
	public void setSetRestTime(int setRestTime) {
		this.setRestTime = setRestTime;
	}
	public int getExerciseRestTime() {
		return exerciseRestTime;
	}
	public void setExerciseRestTime(int exerciseRestTime) {
		this.exerciseRestTime = exerciseRestTime;
	}
}
