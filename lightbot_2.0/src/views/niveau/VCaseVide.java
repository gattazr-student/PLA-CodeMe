package views.niveau;

import models.niveau.CaseVide;

import org.jsfml.graphics.FloatRect;

public class VCaseVide extends VCase {

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
