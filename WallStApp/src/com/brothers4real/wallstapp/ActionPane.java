package com.brothers4real.wallstapp;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

class ActionPane extends JPanel implements IObservable {

	private String name;
	private JLabel stockSymbol;
	private JLabel currentStockValue;
	private Button buy, sell;
	private int clickedButtonId;
	private int minPrice, priceRange;
	
	
	private Date date;
	
	public String getStockName(){
		return name;
	}
	
	public int getStockValue(){
		return Integer.parseInt(currentStockValue.getText());
	}
	public void setStockValue(int i){
		currentStockValue.setText(String.valueOf(i));
	}
	
	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(int priceRange) {
		this.priceRange = priceRange;
	}
	
	//a new variable that stores observers
	private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	public ActionPane (String name, int minPrice, int priceRange){
		this.setMinPrice(minPrice);
		this.setPriceRange(priceRange);
		this.name = name;
		stockSymbol = new JLabel(name);
		currentStockValue = new JLabel("500");
		// current stock price gets added during the runtime!
		buy = new Button("BUY");
		sell = new Button("SELL");
		
		//add controls to the JPanel
		add(stockSymbol);
		add(new JLabel("current price: $"));
		add(currentStockValue);
		add(buy);
		buy.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clickedButtonId = 1;
				date = new Date();
				notifyObservers();
			}
			
		});
		add(sell);
		sell.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clickedButtonId = 2;
				date = new Date();
				notifyObservers();
			}
			
		});
	}
	@Override
	public void registerObserver(IObserver observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (IObserver ob : observers) {
	           
           
				ob.update(clickedButtonId, date, getStockName(), getStockValue());
			
		}
	}

	

}