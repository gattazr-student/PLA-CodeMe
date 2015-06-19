package models.niveau;

import java.util.LinkedList;
import java.util.List;

import models.ObservableModel;

/**
 * Cellule représente un emplacement dans la Carte avec son contenu.
 *
 */
public class Cellule extends ObservableModel {

	/** Liste des cases contenus dans la Cellule */
	private LinkedList<Case> pCases;
	/** Index de l'éléments courant dans pCases */
	private int pCourante;

	/**
	 * Création d'une cellule vide
	 */
	public Cellule() {
		this.pCases = new LinkedList<Case>();
		this.pCourante = 1;
	}

	/**
	 * Ajoute une case dans le cellule.
	 *
	 * @param aCase
	 */
	public void addCase(Case aCase) {
		this.pCases.add(aCase);
	}

	/**
	 * Retourne la case courant de la Cellule
	 *
	 * @return Case courante de la Cellule. null si la Cellule est vide
	 */
	public Case getCase() {
		if (this.pCases.isEmpty()) {
			return null;
		}
		return this.pCases.get(this.pCourante - 1);
	}

	/**
	 * Retounre la liste des Cases contenues dans la cellule
	 *
	 * @return liste de Cases de la Cellule
	 */
	public List<Case> getCases() {
		return this.pCases;
	}

	/**
	 * Retoune l'indice de la cellule courante
	 *
	 * @return indice de la cellule courante
	 */
	public int getCourant() {
		return this.pCourante;
	}

	/**
	 * Change le contenu de la Cellule en changeant la Case Courant.
	 * Le changement est cyclique et donc revient aux contenu original lorsque le dernier contenu a été
	 * atteint.
	 */
	public void nextCourante() {
		if (this.pCourante + 1 > this.pCases.size()) {
			this.pCourante = 0;
		}
		this.pCourante++;
		notifyObserver("cellule_switch", null);
	}

	/**
	 * Reset la Cellul en y remettant le contenu original
	 */
	public void resetCourante() {
		this.pCourante = 1;
		notifyObserver("cellule_reset", null);
	}

}
