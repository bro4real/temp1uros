package com.practicaljava.lesson7;

import com.practicaljava.lesson6.Phone;

public class StationaryPhone extends Phone {
	
	public StationaryPhone(String model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void call(int phoneNumber) {
		// TODO Auto-generated method stub
		System.out.println(getName() + ": I'm calling " + phoneNumber + " from my kitchen.");
	}
	

}
