package com.brothers4real.tabs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.brothers4real.tabs.*;;

public class Engine implements ActionListener {
	
	String message;
	TabPanels parent;
	int number = 0;

	// Constructor stores the reference to the
	// Tab in the member variable parent
	Engine(TabPanels tabs) {
		this.parent = tabs;
	}

	public void actionPerformed(ActionEvent e) {
		// Get the source of this action
		Object src = e.getSource();

		if (src == parent.buttonPlus) {
			number++;
			parent.setDisplayValue(String.valueOf(number));
		} else if (src == parent.buttonMinus) {
			number--;
			parent.setDisplayValue(String.valueOf(number));
		} else {
			number = 0;
			parent.setDisplayValue(String.valueOf(number));
		}
		
		if (Integer.parseInt(parent.getDisplayValue()) > 0){
			message = "Currently positive";
			} else if (Integer.parseInt(parent.getDisplayValue()) < 0) {
			message = "Currently negative";
			} else {
			 message = "Current value: zero";
			}
		parent.setCommentValue(message);
	}
}
