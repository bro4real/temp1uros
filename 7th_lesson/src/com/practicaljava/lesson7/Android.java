package com.practicaljava.lesson7;

import com.practicaljava.lesson6.Programmable;

public class Android extends Smartphone implements Programmable {
	
	public Android(String model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void call(int phoneNumber) {
		// TODO Auto-generated method stub
		System.out.println(getName() + ": I'm calling " + phoneNumber + " from Mountain View, CA.");
	}
	
	//try toString() if it isn't working this way
	public void program(Device d){
		System.out.println("Learn Java and build an app with " + d);
	}

	
	public void browseWeb() {
		// TODO Auto-generated method stub
		System.out.println(getName() +" will take you to google.com");
	}

}
