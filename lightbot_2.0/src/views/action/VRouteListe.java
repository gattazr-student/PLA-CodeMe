package views.action;

import java.util.ArrayList;

import models.action.Action;
import models.action.Route;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;

import views.jsfml.VAction;
import views.jsfml.VRectangle;
import views.jsfml.VRoute;

public class VRouteListe extends VAction {

	private Route pRoute;

	public VRouteListe(Route aRoute, FloatRect aZone) {
		super(aZone);
		this.pRoute = aRoute;
		this.pRoute.addObserver(this);
		initView();
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
				wY = wY + VRoute.HAUTEUR;
			}
			VAction wVAction = VAction.makeVAction(wAction, new FloatRect(wX, wY, VRoute.LARGEUR,
					VRoute.HAUTEUR));
			addView(wVAction);
			wX = wX + VRoute.LARGEUR;
			wI++;
		}
	}

	@Override
	public void update(String aString, Object aObjet) {
		// TODO Auto-generated method stub

	}
}
