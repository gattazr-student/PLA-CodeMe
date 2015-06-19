package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action Divise
 *
 */
public class Divise extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "divise";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* TODO: Divise.apply : make function */
	}

	@Override
	public Action copy() {
		Divise wDivise = new Divise();
		wDivise.setCouleur(getCouleur());
		return wDivise;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		// /* TODO: Divise.valid : make function */
		return false;
	}

}
