package models.action;

import exceptions.LightBotException;
import models.basic.Orientation;
import models.bot.Bot;
import models.niveau.Carte;

/**
 * Action TournerDroite
 *
 */
public class TournerDroite extends Action {

	final static String pNameAction = "droite";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		// apply->tourner_droit
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
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
