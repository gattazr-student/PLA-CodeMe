package views.action;

import models.action.Sauter;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VSauter extends VAction {

	private Sauter pSauter;

	public VSauter(Sauter aSauter, FloatRect aZone) {
		super(aZone);
		this.pSauter = aSauter;
		this.pSauter.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Sauter = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/sauter.png");
		addView(wButton_Sauter);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
