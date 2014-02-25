package com.practicaljava.lesson7;

import com.practicaljava.lesson6.Programmable;

public class iPhone extends Smartphone implements Programmable {

	public iPhone(String model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void call(int phoneNumber) {
		// TODO Auto-generated method stub
		System.out.println(getName() + ": Hello, this is Cupertino calling, i need " + phoneNumber + ".");
	}
	
	@Override
	public void program(Device d) {
		// TODO Auto-generated method stub

		if (d.equals(Device.Mac)){
			System.out.println("Join our Hipster club and build an app with " + d);
		} else {
			System.out.println("Go get a Mac, douchebag!");
		}
	}

	@Override
	public void browseWeb() {
		// TODO Auto-generated method stub
		System.out.println(getName() +" will take you to apple.com");
	}
}
