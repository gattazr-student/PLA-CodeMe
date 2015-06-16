package views.action;

import models.action.Attendre;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VBouton;

public class VAttendre extends VAction {

	private Attendre pAttendre;

	public VAttendre(Attendre aAttendre, FloatRect aZone) {
		super(aZone);
		this.pAttendre = aAttendre;
		this.pAttendre.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Attendre = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/attendre.png");
		addView(wButton_Attendre);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
