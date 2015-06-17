package models.action;

import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;

public class TestAvancer extends Action {

	// ATTENTION : rajouter vérifiaction case suivante libre (non occupée par un robot)

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		Action aAvancer = new Avancer();

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
