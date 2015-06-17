package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action Divise
 *
 */
public class Divise extends Action {

	final static String pNameAction = "divise";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* TODO: Divise.apply : make function */
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		// /* TODO: Divise.valid : make function */
		return false;
	}

}
