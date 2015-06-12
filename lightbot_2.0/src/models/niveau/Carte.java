package models.niveau;

import models.basic.Position;

/**
 * Carte représente le monde d'un Niveau
 *
 */
public class Carte {
	/**
	 * Position maximum sur l'axe des abscisses
	 */
	private int pMaxX;
	/**
	 * Position maximum sur l'axe des ordonnées
	 */
	private int pMaxY;
	/**
	 * Carte
	 */
	private Cellule[][] pCellules;

	/**
	 * Construit une carte en définissant sa taille
	 *
	 * @param aMaxX
	 *            valeur maximum sur l'axe des abcisses
	 * @param aMaxY
	 *            valeur maximum sur l'axe des ordonnées
	 */
	public Carte(int aMaxX, int aMaxY) {
		this.pMaxX = aMaxX;
		this.pMaxY = aMaxY;
		this.pCellules = new Cellule[aMaxY][aMaxX];
		/* Initialise toutes les cellules comme des cellules vides */
		for (int wY = 0; wY < aMaxY; wY++) {
			for (int wX = 0; wX < aMaxX; wX++) {
				this.pCellules[wY][wX] = new Cellule();
			}
		}

	}

	/**
	 * Ajoute une case dans la cellule correspondant à la position de la Case
	 *
	 * @param aCase
	 *            Case à rajouter dans la Cellule
	 */
	public void addCase(Case aCase) {
		Position wPos = aCase.getPosition();
		Cellule wCell = this.pCellules[wPos.getY()][wPos.getX()];
		if (wCell == null) {
			this.pCellules[wPos.getY()][wPos.getX()] = new Cellule();
			wCell = this.pCellules[wPos.getY()][wPos.getX()];
		}
		wCell.addCase(aCase);
	}

	/**
	 * Retourne la Case à la Position donnée dans la Carte
	 *
	 * @param aPosition
	 *            Position à utiliser
	 * @return une Case. Null si il n'y a pas de case à la position donnée.
	 */
	public Case getCase(int aX, int aY) {
		Cellule wCell = this.pCellules[aY][aX];
		if (wCell != null) {
			return wCell.getCase();
		}
		return null;

	}

	/**
	 * Retourne la Case à la Position donnée dans la Carte
	 *
	 * @param aPosition
	 *            Position à utiliser
	 * @return une Case. Null si il n'y a pas de case à la position donnée.
	 */
	public Case getCase(Position aPosition) {
		if (aPosition.getX() < 0 || aPosition.getX() > this.pMaxX - 1 || aPosition.getY() < 0
				|| aPosition.getY() > this.pMaxY - 1) {
			return null;
		}
		Cellule wCell = this.pCellules[aPosition.getY()][aPosition.getX()];
		if (wCell != null) {
			return wCell.getCase();
		}
		return null;
	}

	/**
	 * Retourne la e tableau de Cellul contenant la carte
	 *
	 * @return HashMap<Position, Case> des Cases de la Carte
	 */
	public Cellule[][] getCases() {
		return this.pCellules;
	}

	/**
	 * Retourne la cellule a la position donnée
	 * 
	 * @param aPosition
	 *            position de la cellule
	 * @return la cellule a la position donnée
	 */
	public Cellule getCellule(Position aPosition) {
		if (aPosition.getX() < 0 || aPosition.getX() > this.pMaxX - 1 || aPosition.getY() < 0
				|| aPosition.getY() > this.pMaxY - 1) {
			return null;
		}
		return this.pCellules[aPosition.getY()][aPosition.getX()];
	}

	/**
	 * Retourne la valeur maximum de la Position des Cases sur l'axe des abscisses
	 *
	 * @return valeur maximum sur l'axe des abscisses
	 */
	public int getMaxX() {
		return this.pMaxX;
	}

	/**
	 * Retourne la valeur maximum de la Position des Cases sur l'axe des ordonnées
	 *
	 * @return valeur maximum sur l'axe des ordonnées
	 */
	public int getMaxY() {
		return this.pMaxY;
	}

	/**
	 * Définit la taille de la Carte en définissant les valeurs maximum sur l'axe des Absicess et des
	 * ordonnées des Positions des Cases
	 *
	 * @param aMaxX
	 *            nouvelle valeur maximum sur l'axe des abscisses
	 * @param aMaxY
	 *            nouvelle valeur maximum sur l'axe des ordonnées
	 */
	public void setMaxSize(int aMaxX, int aMaxY) {
		this.pMaxX = aMaxX;
		this.pMaxY = aMaxY;
	}

	/**
	 * Modification de la Case courante dans la Cellule à la position donnée.
	 *
	 * @param aPosition
	 *            Position de la cellule à modifier
	 */
	public void switchCase(Position aPosition) {
		this.pCellules[aPosition.getY()][aPosition.getX()].nextCourante();
	}
}
