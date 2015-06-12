package models.niveau;

import models.ObservableModel;
import models.basic.Position;

/**
 * Case représente une case dans un Niveau.
 *
 */
public abstract class Case extends ObservableModel {

	private Position pPosition;
	private int pHauteur;

	/**
	 * Suppression du constructeur sans paramètre
	 */
	@SuppressWarnings("unused")
	private Case() {
	}

	/**
	 * Construit une case selon sa position et sa hauteur
	 *
	 * @param aPosition
	 * @param aHauteur
	 */
	public Case(Position aPosition, int aHauteur) {
		this.pPosition = aPosition;
		this.pHauteur = aHauteur;
	}

	/**
	 * Comportement de la case lorsque le bot effectue l'action Allumer dessus
	 */
	public abstract void activate();

	/**
	 * Retourne la hauteur de la Case
	 *
	 * @return hauteur de la Case
	 */
	public final int getHauteur() {
		return this.pHauteur;
	}

	/**
	 * Retourne la Position de la Case
	 *
	 * @return Position de la Case
	 */
	public final Position getPosition() {
		return this.pPosition;
	}

}
