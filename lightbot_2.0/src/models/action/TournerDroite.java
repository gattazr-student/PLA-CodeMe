package models.action;

import models.basic.Orientation;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action TournerDroite
 *
 */
public class TournerDroite extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "droite";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		if (aBot.getOrientation() == Orientation.EST) {
			aBot.setOrientation(Orientation.SUD);
		} else if (aBot.getOrientation() == Orientation.SUD) {
			aBot.setOrientation(Orientation.OUEST);
		} else if (aBot.getOrientation() == Orientation.OUEST) {
			aBot.setOrientation(Orientation.NORD);
		} else {
			aBot.setOrientation(Orientation.EST);
		}
	}

	@Override
	public Action copy() {
		TournerDroite wTournerDroite = new TournerDroite();
		wTournerDroite.setCouleur(getCouleur());
		return wTournerDroite;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
