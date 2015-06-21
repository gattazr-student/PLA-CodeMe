package models.cases;

import models.ObservableModel;
import models.basic.Position;

/**
 * Case représente une case dans un Niveau.
 *
 */
public abstract class Case extends ObservableModel {

	/** Position de la Case */
	private Position pPosition;
	/** Hauteur de la Case */
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
	 *            Position de la Case
	 * @param aHauteur
	 *            hauteur de la Case
	 */
	public Case(Position aPosition, int aHauteur) {
		this.pPosition = aPosition;
		this.pHauteur = aHauteur;
	}

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
