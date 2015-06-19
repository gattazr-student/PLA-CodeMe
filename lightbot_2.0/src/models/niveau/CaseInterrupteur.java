package models.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;

/**
 * CaseInterrupteur représentant une case qui va permettre la modificiation de la Carte dans laquelle elle se
 * trouve
 */
public class CaseInterrupteur extends Case {

	/** Position des Cellules dont il faut changer le contenu */
	private List<Position> pPositions;

	/**
	 * Construit une case Interrupteur à la Position et la hauteur donnée
	 *
	 * @param aPosition
	 *            Position de la Case
	 * @param aHauteur
	 *            hauteur de la Case
	 */
	public CaseInterrupteur(Position aPosition, int aHauteur) {
		super(aPosition, aHauteur);
		this.pPositions = new LinkedList<Position>();
	}

	/**
	 * Ajout un élément dans la liste des positions des Cellules à modifier
	 *
	 * @param aPosition
	 *            Position de la Cellule à modifier
	 */
	public void addPosition(Position aPosition) {
		this.pPositions.add(aPosition);
	}

	/**
	 * Change le contenu de toutes les Cellules de la Carte donnée dont la position est stocké dans la liste
	 * de position de la
	 * classe
	 *
	 * @param aCarte
	 *            Carte à modifier
	 */
	public void changeCase(Carte aCarte) {
		for (Position wPosition : this.pPositions) {
			Cellule wCell = aCarte.getCellule(wPosition);
			if (wCell != null) {
				wCell.nextCourante();
			}
		}

	}

}
