package models.action;

import models.bot.Bot;
import models.niveau.Carte;

/**
 * Action Attendre
 *
 */
public class Attendre extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/* TODO: */
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
