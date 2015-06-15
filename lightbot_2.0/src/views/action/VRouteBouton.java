package views.action;

import models.action.Route;

import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VBouton;

public class VRouteBouton extends VAction {

	private Route pRoute;

	public VRouteBouton(Route aRoute, FloatRect aZone) {
		super(aZone);
		this.pRoute = aRoute;
		this.pRoute.addObserver(this);
		initView();
	}

	@Override
	public void initView() {
		String wName = this.pRoute.getName();
		StringBuilder wNameImage = new StringBuilder();
		wNameImage.append("res/action/route_");
		wNameImage.append(wName);
		wNameImage.append(".png");
		VBouton wButton_RouteBouton = new VBouton(new FloatRect(0, 0, getWidth(), getHeight()),
				wNameImage.toString());
		addView(wButton_RouteBouton);
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}
}
