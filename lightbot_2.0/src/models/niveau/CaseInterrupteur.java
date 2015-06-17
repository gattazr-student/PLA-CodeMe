package models.niveau;

import java.util.LinkedList;
import java.util.List;

import models.basic.Position;

public class CaseInterrupteur extends Case {

	/**
	 * Position des Cellules dont il faut changer le contenu
	 */
	private List<Position> pPositions;

	public CaseInterrupteur(Position aPosition, int aHauteur) {
		super(aPosition, aHauteur);
		this.pPositions = new LinkedList<Position>();
	}

	public void addPosition(Position aPosition) {
		this.pPositions.add(aPosition);
	}

	public void changeCase(Carte aCarte) {
		for (Position wPosition : this.pPositions) {
			Cellule wCell = aCarte.getCellule(wPosition);
			if (wCell != null) {
				wCell.nextCourante();
			}
		}

	}

}
