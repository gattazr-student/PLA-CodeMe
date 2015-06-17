package views.niveau;

import models.niveau.CaseVide;

import org.jsfml.graphics.FloatRect;

public class VCaseVide extends VCase {

	private CaseVide pCaseVide;

	public VCaseVide(CaseVide aCaseVide, FloatRect aZone) {
		super(aZone);
		this.pCaseVide = aCaseVide;
		this.pCaseVide.addObserver(this);
		setHauteur(this.pCaseVide.getHauteur());
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
