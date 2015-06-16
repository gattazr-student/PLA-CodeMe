package views.action;

import models.action.Action;
import models.action.Allumer;
import models.action.Attendre;
import models.action.Avancer;
import models.action.Divise;
import models.action.Route;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;

import views.View;

public abstract class VAction extends View implements Observer {

	public static VAction makeVAction(Action aAction, FloatRect aZone) {
		if (aAction instanceof Allumer) {
			return new VAllumer((Allumer) aAction, aZone);
		}
		if (aAction instanceof Attendre) {
			return new VAttendre((Attendre) aAction, aZone);
		}
		if (aAction instanceof Avancer) {
			return new VAvancer((Avancer) aAction, aZone);
		}
		if (aAction instanceof Divise) {
			return new VDivise((Divise) aAction, aZone);
		}
		if (aAction instanceof Route) {
			return new VRouteBouton((Route) aAction, aZone);
		}
		if (aAction instanceof Sauter) {
			return new VSauter((Sauter) aAction, aZone);
		}
		if (aAction instanceof TournerDroite) {
			return new VTournerDroite((TournerDroite) aAction, aZone);
		}
		if (aAction instanceof TournerGauche) {
			return new VTournerGauche((TournerGauche) aAction, aZone);
		}
		return null;
	}

	public VAction(FloatRect aZone) {
		setZone(aZone);
	}
}
