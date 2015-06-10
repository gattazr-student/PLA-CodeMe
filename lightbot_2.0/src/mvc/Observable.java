package mvc;

/**
 * Observable . Un Observable est un Objet qui va notifier tous ses observeurs d'évènements.
 *
 */
public interface Observable {

	/**
	 * Ajoute un Observer à l'Observable
	 *
	 * @param aObserver
	 *            Observer à ajouter
	 */
	public void addObserver(Observer aObserver);

	/**
	 * Notification de tous les Observers d'un évènement
	 *
	 * @param aString
	 *            identifiant de l'évènement
	 * @param aObjet
	 *            Objet accompagnant l'évènement si necessaire
	 */
	public void notifyObserver(String aString, Object aObjet);

	/**
	 * Retire tous les Observers
	 */
	public void removeObservers();
}
