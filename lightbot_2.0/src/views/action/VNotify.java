package views.action;

import models.action.Notify;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VNotify extends VAction {

	private Notify pNotify;

	public VNotify(Notify aNotify, FloatRect aZone) {
		super(aZone);
		this.pNotify = aNotify;
		this.pNotify.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		VBouton wButton_Notify = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				"res/action/notify.png");
		addView(wButton_Notify);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}

}
