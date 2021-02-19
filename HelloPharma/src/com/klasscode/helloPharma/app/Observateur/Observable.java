package com.klasscode.helloPharma.app.Observateur;

public interface Observable {
	
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str);
}
