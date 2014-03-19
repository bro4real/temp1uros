package com.brothers4real.wallstapp;

import java.util.Random;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Display extends TimerTask {
	
	private JFrame frame;
	private JPanel container;
	private int price;
	private int minPrice;
	private int priceRange;
	private ActionPane action; // = new ActionPane("AAPL");
	// Action has to be instantiated outside of 
	// the Constructor because its currentStockValue
	// field needs to be accessed from the MessagePane.
	
	private MessagePane message = new MessagePane();

	public Display (JFrame frame, JPanel container, ActionPane action, int width, int height){
		
		// Assign passed values:
		this.frame = frame;
		this.container = container;
		this.action = action;
		
		// Define and populate containing JPanel:
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(action);
		container.add(message);
		
		// Start observing:
		action.registerObserver(message);
		

		frame.setBounds(200, 200, width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Create volatile price!
		price = new Random().nextInt(action.getPriceRange()) + action.getMinPrice();
		action.setStockValue(price);
		
		//Keep setting content over and over again...
		this.frame.setContentPane(container);
			
	}
		
}