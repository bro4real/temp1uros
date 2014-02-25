package com.practicaljava.lesson7;

import com.practicaljava.lesson6.Phone;

public class Mobile extends Phone {

	public Mobile(String model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void call(int phoneNumber) {
		// TODO Auto-generated method stub
		System.out.println(getName() + ": I randomly chose a number cause I'm calling from my car!");
	}
	
}
