package views.action;

import java.util.ArrayList;

import models.action.Action;
import models.action.Route;
import mvc.Observer;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;

import views.View;
import views.jsfml.VRectangle;

public class VRouteListe extends View implements Observer {

	private Route pRoute;

	public VRouteListe(Route aRoute, FloatRect aZone) {
		super(aZone);
		this.pRoute = aRoute;
		this.pRoute.addObserver(this);
		initView();
	}

	public Route getRoute() {
		return this.pRoute;
	}

	@Override
	public void initView() {
		VRectangle wContainer = new VRectangle(new FloatRect(0, 0, getWidth(), getHeight()));
		wContainer.setFillColor(Color.YELLOW);
		addView(wContainer);
		int wI = 0;
		int wX = 0;
		int wY = 0;
		ArrayList<Action> wActions = this.pRoute.getAction();
		for (Action wAction : wActions) {
			if (wI == 4) {
				wI = 0;
				wX = 0;
				wY = wY + VAction.HAUTEUR;
			}
			VAction wVAction = VAction.makeVAction(wAction, new FloatRect(wX, wY, VAction.LARGEUR,
					VAction.HAUTEUR));
			addView(wVAction);
			wX = wX + VAction.LARGEUR;
			wI++;
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
		if (aString.equals("route_add")) {
			clearView();
			initView();
		}
		if (aString.equals("route_remove")) {
			clearView();
			initView();
		}
	}
}
