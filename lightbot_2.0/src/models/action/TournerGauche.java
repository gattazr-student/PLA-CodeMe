package models.action;

import exceptions.LightBotException;
import models.basic.Orientation;
import models.bot.Bot;
import models.niveau.Carte;

/**
 * Action TournerGauche
 *
 */
public class TournerGauche extends Action {

	final static String pNameAction = "gauche";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		if (aBot.getOrientation() == Orientation.EST) {
			aBot.setOrientation(Orientation.NORD);
		} else if (aBot.getOrientation() == Orientation.NORD) {
			aBot.setOrientation(Orientation.OUEST);
		} else if (aBot.getOrientation() == Orientation.OUEST) {
			aBot.setOrientation(Orientation.SUD);
		} else {
			aBot.setOrientation(Orientation.EST);
		}
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
