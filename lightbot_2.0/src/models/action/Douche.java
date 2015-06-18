package models.action;

import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class Douche extends Action {

	final static String pNameAction = "douche";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		aBot.setCouleur(Couleur.BLANC);
	}

	@Override
	public Action copy() {
		Douche wDouche = new Douche();
		wDouche.setCouleur(getCouleur());
		return wDouche;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}

}
