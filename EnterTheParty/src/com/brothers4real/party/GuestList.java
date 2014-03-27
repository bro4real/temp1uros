package com.brothers4real.party;

import java.util.ArrayList;
import java.util.Set;


public class GuestList <E> extends ArrayList<E>{

	private E e;
	
	public void addGuest(E e){
		this.e = e;
	}
	
	public E get() {
		return e;
	}
	
}
