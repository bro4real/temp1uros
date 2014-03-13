package com.brothers4real.water;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class Engine implements ActionListener, Observer, FocusListener {
	
	
	TabPanels parent;
	int number = 0;
	String message;
	// Constructor stores the reference to the
	// TabPanels in the member variable parent
	Engine(TabPanels tabs) {
		this.parent = tabs;
	}

	public void actionPerformed(ActionEvent e) {
		// Get the source of this action
		Object src = e.getSource();
		
		if (src == parent.buttonPlus) {
			number++;
			} else if (src == parent.buttonMinus) {
			number--;
			} else if (src == parent.buttonReset){
			number = 0;
			}
		parent.setDisplayValue(String.valueOf(number));
		parent.notifyObservers();
		
	}
	
	
	@Override
	public void update(int displayedValue) {
		// TODO Auto-generated method stub
		int i = displayedValue;
		if (i > 0){
			message = "Ice is melting.";
			} else if (i < 0) {
			message = "Water is freezing.";
			} else {
			 message = "Current temperature: zero";
			}
		parent.setCommentValue(message);
	}


	

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		try{
			parent.notifyObservers();
			number = Integer.parseInt(parent.getDisplayValue());
			} catch (Exception e){
				
			}
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		
		// I had to implement it even though I don't need it!
	}
}

