package models;

import java.util.LinkedList;
import java.util.List;

import mvc.Observable;
import mvc.Observer;

/**
 * Model Observable
 *
 */
public class ObservableModel implements Observable {

	/** Liste des Observer sur le model Observable */
	private List<Observer> pObservers;

	/**
	 * Création du model Observable
	 */
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
