package models;

import java.util.LinkedList;
import java.util.List;

import mvc.Observable;
import mvc.Observer;

public class ObservableModel implements Observable {

	public List<Observer> pObservers;

	public ObservableModel() {
		this.pObservers = new LinkedList<Observer>();
	}

	@Override
	public void addObserver(Observer aObserver) {
		this.pObservers.add(aObserver);
	}

	@Override
	public void notifyObserver(String aString, Object aObjet) {
		/* TODO: REMOVE AFTER DEBUG */
		System.out.println(String.format("!! EVENT !! %s", aString));
		for (Observer wObs : this.pObservers) {
			wObs.update(aString, aObjet);
		}
	}

	@Override
	public void removeObservers() {
		this.pObservers.clear();
	}

}
