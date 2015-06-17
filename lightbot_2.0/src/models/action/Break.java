package models.action;

import models.bot.Bot;
import models.niveau.Carte;

public class Break extends Action {

	final static String pNameAction = "break";

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/* L'action Break ne peut pas être appliqué. */
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* L'action Break ne peut pas être appliquée. */
		return false;
	}

}
