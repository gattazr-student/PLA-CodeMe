package views.action;

import models.action.Avancer;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VAvancer extends VAction {

	private Avancer pAvancer;

	public VAvancer(Avancer aAvancer, FloatRect aZone) {
		super(aZone);
		this.pAvancer = aAvancer;
		this.pAvancer.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Avancer = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/avancer.png");
		addView(wButton_Avancer);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
