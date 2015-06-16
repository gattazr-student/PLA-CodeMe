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
		/* TODO: QUICK FIX TRES MAUVAIS */
		/* Real Fix : Ne pas remplacer les objets VBOts lors VCarte.update() */
		try {
			System.out.println(aString);
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
