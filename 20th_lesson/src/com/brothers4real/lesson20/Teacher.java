package com.brothers4real.lesson20;



public class Teacher extends Thread {

	static final int LESSON_NUMBER = 20;
	Teachable person;
	
	public Teacher(Teachable t){
		this.person = t;
	}
	
	public void run(){
			
			synchronized(person){
				person.setAssignment(LESSON_NUMBER);
				person.notify();
			}
	}
}
