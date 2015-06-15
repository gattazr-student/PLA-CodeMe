package views.action;

import models.action.Allumer;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VAllumer extends VAction {

	private Allumer pAllumer;

	public VAllumer(Allumer aAllumer, FloatRect aZone) {
		super(aZone);
		this.pAllumer = aAllumer;
		this.pAllumer.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Allumer = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/allume.png");
		addView(wButton_Allumer);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}
}
