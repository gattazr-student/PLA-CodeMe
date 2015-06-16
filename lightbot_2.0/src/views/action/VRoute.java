package views.action;

import models.action.Route;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;

import views.View;

public class VRoute extends View implements Observer {

	public static final int HAUTEUR = 53;
	public static final int LARGEUR = 52;

	public VRoute(FloatRect aZone, Route aRoute) {
		setZone(aZone);
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}
}
