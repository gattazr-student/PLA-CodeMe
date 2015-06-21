package models.cases;

import models.basic.Position;

/**
 * CaseBasique représente une case n'ayant aucun comportement particulier
 *
 */
public class CaseBasique extends Case {

	/**
	 * Création d'une CaseBasique en définissant sa Position et sa hauteur
	 *
	 * @param aPosition
	 *            Position de la Case
	 * @param aHauteur
	 *            hauteur de la Case
	 */
	public CaseBasique(Position aPosition, int aHauteur) {
		super(aPosition, aHauteur);
	}

}
