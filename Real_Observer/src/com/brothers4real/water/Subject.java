package com.brothers4real.water;

public interface Subject {
	public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
