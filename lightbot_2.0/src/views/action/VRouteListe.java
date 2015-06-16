package views.action;

import java.util.ArrayList;

import models.action.Action;
import models.action.Route;
import mvc.Observer;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;

import views.View;
import views.jsfml.VRectangle;
import views.jsfml.VTexte;

public class VRouteListe extends View implements Observer {

	public static final int OFFSET = 35;

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

		VTexte wTexte = new VTexte(new FloatRect(0, 0, 0, 0), this.pRoute.getName(), 25);
		wTexte.setColor(Color.BLACK);
		addView(wTexte);

		int wI = 0;
		int wX = 0;
		int wY = VRouteListe.OFFSET;
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
