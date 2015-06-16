package views.action;

import models.action.Divise;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VBouton;

public class VDivise extends VAction {

	private Divise pDivise;

	public VDivise(Divise aDivise, FloatRect aZone) {
		super(aZone);
		this.pDivise = aDivise;
		this.pDivise.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Divise = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/divise.png");
		addView(wButton_Divise);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
