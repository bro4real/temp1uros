package com.brothers4real.party;

public abstract class Vip {
	String firstName;
	String lastName;
	
	public Vip (String fn, String ln){
		firstName = fn;
		lastName = ln;
	}
	
	public void printGuest(){
		System.out.println(firstName + " " + lastName + " is on the list.");
	}
	
}
