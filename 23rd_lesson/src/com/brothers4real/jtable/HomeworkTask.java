package com.brothers4real.jtable;

public class HomeworkTask {

	public int taskID;
	public String taskDescription;
	public String minutesNeeded;

	public HomeworkTask(int id, String description, String duration) {
		taskID = id;
		taskDescription = description;
		minutesNeeded = duration;
	}

}
