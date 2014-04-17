package com.brothers4real.lesson20;

public class Student implements Teachable {
	
	// I made these fields public for
	// the sake of code simplicity:
	public int lessonNumber;
	public String name;
	
	// I didn't want each student to have
	// his own teacher (living on separate thread),
	// that's why I declared this field static:
	static Teacher myTeacher;
	
	public Student (String name){
		this.name = name;
	}
	
	@Override
	public void requestHomework() {
		
		myTeacher = new Teacher(this);
		
		// But I was wondering whether a new thread was being made
		// anyway, on each callback of the following method:
		myTeacher.start();
		
		synchronized(this) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setAssignment(int lessonNo) {
		// TODO Auto-generated method stub
		lessonNumber = lessonNo;
	}

}
