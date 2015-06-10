package models.action;

import models.bot.Bot;
import models.niveau.Carte;

/**
 * Ensemble d'Action à effectuer
 *
 */
public class Route extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/* TODO make function */

	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* On peut toujours évaluer une route */
		return true;
	}

}
