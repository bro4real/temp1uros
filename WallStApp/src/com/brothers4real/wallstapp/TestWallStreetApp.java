package com.brothers4real.wallstapp;

import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestWallStreetApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActionPane action1 = new ActionPane("AAPL", 500, 100);
		
		JPanel container = new JPanel();
		JFrame mainFrame = new JFrame("STOCKBROKER");
		
		Display BrokersHelper = new Display(mainFrame, container, action1, 500, 300);
		Timer t = new Timer();
		t.schedule(BrokersHelper, 0, 1000);
			
	}
	
}
