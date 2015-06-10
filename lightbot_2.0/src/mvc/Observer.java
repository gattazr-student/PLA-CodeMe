package mvc;

/**
 * Observer interface. Un Observer est notifié des modifications de l'Observable qu'il observe à travers la
 * méthode update
 *
 */
public interface Observer {
	/**
	 * Notification d'un évènement sur l'Observable
	 *
	 *
	 * @param aString
	 *            Identifiant de l'évènement
	 * @param aObjet
	 *            Objet accompagnant l'évènement si necessaire
	 */
	public void update(String aString, Object aObjet);
}