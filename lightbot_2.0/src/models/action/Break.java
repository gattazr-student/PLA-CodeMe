package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class Break extends Action {

	final static String pNameAction = "break";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* L'action Break ne peut pas être appliqué. */
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return false;
	}

}
