package models.niveau;

import models.ObservableModel;
import models.basic.Position;
import models.niveau.CaseLampe.ETAT_LAMPE;

/**
 * Carte représente le monde d'un Niveau
 *
 */
public class Carte extends ObservableModel {
	/** Position maximum sur l'axe des abscisses */
	private int pMaxX;
	/** Position maximum sur l'axe des ordonnées */
	private int pMaxY;
	/** Cellules composant la Ccarte */
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
		if (aCase != null) {
			Position wPos = aCase.getPosition();
			if (!positionValid(wPos)) {
				return;
			}
			Cellule wCell = this.pCellules[wPos.getY()][wPos.getX()];
			if (wCell == null) {
				this.pCellules[wPos.getY()][wPos.getX()] = new Cellule();
				wCell = this.pCellules[wPos.getY()][wPos.getX()];
			}
			wCell.addCase(aCase);
		}
	}

	/**
	 * Retourne la Case à la Position donnée dans la Carte
	 *
	 * @param aX
	 *            Abscisse
	 * @param aY
	 *            Ordonnée
	 * @return une Case. Null si il n'y a pas de case à la position donnée.
	 */
	public Case getCase(int aX, int aY) {
		if (!positionValid(aX, aY)) {
			return null;
		}
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
	 * @return une Case. Null si il n'y a pas de case à la position donnée où la position n'existe pas.
	 */
	public Case getCase(Position aPosition) {
		if (aPosition == null) {
			return null;
		}
		return getCase(aPosition.getX(), aPosition.getY());
	}

	/**
	 * Retourne la cellule à la position donnée
	 *
	 * @param aPosition
	 *            position de la cellule
	 * @return la cellule a la position donnée
	 */
	public Cellule getCellule(Position aPosition) {
		if (!positionValid(aPosition)) {
			return null;
		}
		return this.pCellules[aPosition.getY()][aPosition.getX()];
	}

	/**
	 * Retourne le tableau de Cellule contenant la carte
	 *
	 * @return Tableau à deux dimensions de Cellules
	 */
	public Cellule[][] getCellules() {
		return this.pCellules;
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
	 * Retourne vrai si la position passé en paramètre est atteignable dans la carte
	 *
	 * @param aX
	 *            Abscisses de la position
	 * @param aY
	 *            Ordonnée de la position
	 * @return true si la position passé en paramètre est atteignable dans la Carte
	 */
	private boolean positionValid(float aX, float aY) {
		if (aX < 0 || aX > this.pMaxX - 1 || aY < 0 || aY > this.pMaxY - 1) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne vrai si la position passé en paramètre est atteignable dans la carte
	 *
	 * @param aX
	 *            Abscisses de la position
	 * @param aY
	 *            Ordonnée de la position
	 * @return true si la position passé en paramètre est atteignable dans la Carte
	 */
	private boolean positionValid(Position aPosition) {
		return positionValid(aPosition.getX(), aPosition.getY());
	}

	/**
	 * Remet la Carte dans son état Original
	 */
	public void reset() {
		for (int wY = 0; wY < this.pMaxY; wY++) {
			for (int wX = 0; wX < this.pMaxX; wX++) {
				this.pCellules[wY][wX].resetCourante();
				Case wCase = this.pCellules[wY][wX].getCase();
				if (wCase instanceof CaseLampe) {
					if (((CaseLampe) wCase).getEtat() == ETAT_LAMPE.ALLUMEE) {
						((CaseLampe) wCase).activate();
					}
				}
			}
		}
		notifyObserver("carte_reset", null);
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
		if (!positionValid(aPosition)) {
			return;
		}
		Cellule wCell = this.pCellules[aPosition.getY()][aPosition.getX()];
		if (wCell == null) {
			return;
		}
		wCell.nextCourante();
		notifyObserver("carte_switch", null);
	}
}
