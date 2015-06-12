package models.niveau;

import java.util.LinkedList;
import java.util.List;

public class Cellule {

	private LinkedList<Case> pCases;
	private int pCourante;

	public Cellule() {
		this.pCases = new LinkedList<Case>();
		this.pCourante = 1;
	}

	/**
	 * Ajout une case dans le cellule. la première case ajouté à une cellule est celle par défault
	 *
	 * @param aCase
	 */
	public void addCase(Case aCase) {
		this.pCases.add(aCase);
	}

	/**
	 * Retourne la case courant de la Cellule
	 *
	 * @return Case courant de la Cellule. null si la Cellule est vide
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
	 * @return liste de Cases
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

	public void nextCourante() {
		if (this.pCourante + 1 > this.pCases.size()) {
			// throw new Exception("Pas assez de case dans la cellule");
			return;
		}
		this.pCourante++;
	}

	public void resetCourante() {
		this.pCourante = 1;
	}

}
