package models.action;

import models.basic.Etat;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action Attendre
 *
 */
public class Attendre extends Action {

	final static String pNameAction = "wait";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		aBot.setEtat(Etat.PASSIF);
	}

	@Override
	public Action copy() {
		Attendre wAttendre = new Attendre();
		wAttendre.setCouleur(getCouleur());
		return wAttendre;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
