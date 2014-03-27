package com.brothers4real.party;

public class TestGuestList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Actor guest1 = new Actor("Tom", "Criuse");
		Entertainer guest2 = new Singer("Bono", "Vox");
		Vip guest3 = new Politician("George", "Bush");
		
		GuestList <Entertainer> list1 = new GuestList<>();
		
		// parameterized type of list1 is "Entertainer"
		// and not "? extends Entertainer", but it still
		// accepts guest1 - instance of a subclass, WHY?
		list1.add(guest1); 
		list1.add(guest2);

		for(Vip x : list1){	
			x.printGuest();
		}
		
		GuestList<? super Entertainer> list2 = new GuestList<>();
		list2.add(guest1); 
		list2.add(guest2);
		//list2.add(guest3);
		
		// Compiler complains when I try to add
		// object of a super class, class Vip, WHY?
		
		
		for(Object x : list2){	
			Entertainer y = (Entertainer) x;
			y.printGuest();
		}
		
	}

}
