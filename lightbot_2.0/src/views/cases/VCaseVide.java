package views.cases;

import models.cases.CaseVide;

import org.jsfml.graphics.FloatRect;

/**
 * Représentation graphique d'une CaseVide
 *
 */
public class VCaseVide extends VCase {

	/**
	 *
	 * @param aCaseVide
	 *            Case à représenter
	 * @param aZone
	 *            Position et taille de la VCaseVide
	 */
	public VCaseVide(CaseVide aCaseVide, FloatRect aZone) {
		super(aCaseVide, aZone);
		aCaseVide.addObserver(this);
		setHauteur(aCaseVide.getHauteur());
		initView();
	}

	@Override
	public void initView() {
		/* Rien a dessiner */
	}

	@Override
	public void update(String aString, Object aObjet) {
		/* Aucune mise a jour à faire */
	}
}
