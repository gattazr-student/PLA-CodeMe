package models.niveau;

import models.basic.Position;

/**
 * Case Vide représente une case qui n'est pas accessible
 *
 */
public class CaseVide extends Case {

	public CaseVide(Position aPosition) {
		super(aPosition, 0);
	}

}
