package views.action;

import models.action.TournerDroite;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VBouton;

public class VTournerDroite extends VAction {

	private TournerDroite pTournerDroite;

	public VTournerDroite(TournerDroite aTournerDroite, FloatRect aZone) {
		super(aZone);
		this.pTournerDroite = aTournerDroite;
		this.pTournerDroite.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_TournerDroite = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/tourner_droit.png");
		addView(wButton_TournerDroite);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
