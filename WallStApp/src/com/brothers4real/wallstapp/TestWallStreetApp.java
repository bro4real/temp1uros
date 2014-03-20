package com.brothers4real.wallstapp;

import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestWallStreetApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame mainFrame = new JFrame("STOCKBROKER");
		JPanel container = new JPanel();
		
		// this constructor's parameters: name of the stock, 
		// its minimal price, and price range
		ActionPane action1 = new ActionPane("AAPL", 500, 100);
		
		// this constructor's parameters: JFrame, JPanel, 
		// ActionPane, window's width and height
		Display BrokersHelper = new Display(mainFrame, container, action1, 500, 300);
		Timer t = new Timer();
		t.schedule(BrokersHelper, 0, 1000);
			
	}
	
}
