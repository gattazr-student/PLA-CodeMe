package models.action;

import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class TestAvancer extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		Avancer aAvancer = new Avancer();

		if (aAvancer.valid(aBot, aCarte)) {
			aBot.setCouleur(Couleur.VERT);
		} else {
			aBot.setCouleur(Couleur.ROUGE);
		}
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}
}
