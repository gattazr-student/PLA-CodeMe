package views.action;

import models.action.Break;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VBreak extends VAction {

	private Break pBreak;

	public VBreak(Break aBreak, FloatRect aZone) {
		super(aZone);
		this.pBreak = aBreak;
		this.pBreak.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Break = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/break.png");
		addView(wButton_Break);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}
}
