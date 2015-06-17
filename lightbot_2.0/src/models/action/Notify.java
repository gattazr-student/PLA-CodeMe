package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class Notify extends Action {

	final static String pNameAction = "notify";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* TODO Notify.apply : make function */

	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* TODO Notify.valid : make function */
		return false;
	}

}
