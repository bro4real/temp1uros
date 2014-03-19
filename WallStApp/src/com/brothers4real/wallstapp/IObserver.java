package com.brothers4real.wallstapp;

import java.util.Date;

public interface IObserver {

	public void update(int clickedButtonId, Date date, String symbol, int value);
	
}
