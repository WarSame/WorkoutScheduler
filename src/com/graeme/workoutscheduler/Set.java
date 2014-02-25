package com.graeme.workoutscheduler;

import java.io.Serializable;

public class Set implements Serializable{
	private static final long serialVersionUID = 1L;
	int repCount;
	int repWeight;
	Set(){
		repCount = 0;
		repWeight = 0;
	}
	public int getRepCount() {
		return repCount;
	}
	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}
	public int getRepWeight() {
		return repWeight;
	}
	public void setRepWeight(int repWeight) {
		this.repWeight = repWeight;
	}
}
