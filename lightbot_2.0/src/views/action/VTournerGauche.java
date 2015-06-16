package views.action;

import models.action.TournerGauche;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VBouton;

public class VTournerGauche extends VAction {

	private TournerGauche pTournerGauche;

	public VTournerGauche(TournerGauche aTournerGauche, FloatRect aZone) {
		super(aZone);
		this.pTournerGauche = aTournerGauche;
		this.pTournerGauche.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_TournerGauche = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/tourner_gauche.png");
		addView(wButton_TournerGauche);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
