package models;

import java.util.LinkedList;
import java.util.List;

import mvc.Observable;
import mvc.Observer;

public class ObservableModel implements Observable {

	private List<Observer> pObservers;

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
		System.out.println(String.format("!! EVENT !!  %s : %s", getClass().getSimpleName(), aString));

		/* TODO: QUICK FIX TRES MAUVAIS */
		try {
			for (Observer wObs : this.pObservers) {
				wObs.update(aString, aObjet);
			}
		} catch (Exception aException) {

		}
	}

	@Override
	public void removeObservers() {
		this.pObservers.clear();
	}

}
