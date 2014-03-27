package com.brothers4real.party;

public class Test4Duplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vip guest1 = new Actor("Tom", "Criuse");
		Vip guest2 = new Singer("Bono", "Vox");
		Vip guest3 = new Politician("George", "Bush");
		
		NoDuplicatesList<Vip> list4 = new NoDuplicatesList<>();
		
		list4.addItem(guest1);
		list4.addItem(guest2);
		list4.addItem(guest3);
		list4.addItem(guest1);
		
		for(Vip x : list4){	
			x.printGuest();
		}
	}

}
