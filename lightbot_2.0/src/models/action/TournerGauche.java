package models.action;

import models.basic.Orientation;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action TournerGauche
 *
 */
public class TournerGauche extends Action {

	/** Nom de l'Action */
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
	public Action copy() {
		TournerGauche wTournerGauche = new TournerGauche();
		wTournerGauche.setCouleur(getCouleur());
		return wTournerGauche;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
