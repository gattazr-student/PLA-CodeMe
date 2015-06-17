package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class Notify extends Action {

	final static String pNameAction = "notify";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* Notify ne doit pas être appliqué */
		throw new LightBotException("Apply ne peut pas etre applique");
	}

	@Override
	public Action copy() {
		Notify wNotify = new Notify();
		wNotify.setCouleur(getCouleur());
		return wNotify;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return false;
	}

}
