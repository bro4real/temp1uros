package com.practicaljava.lesson6;

public abstract class Phone {
	
	String model;
	
	public Phone (String model){
		this.model = model;	
	}
	
	public String getName(){
		return model;
	}
	
	public abstract void call(int phoneNumber);

}
