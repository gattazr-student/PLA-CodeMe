package models.action;

import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

public class TestSauter extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		Sauter aSauter = new Sauter();

		if (aSauter.valid(aBot, aCarte)) {
			aBot.setCouleur(Couleur.VERT);
		} else {
			aBot.setCouleur(Couleur.ROUGE);
		}
	}

	@Override
	public Action copy() {
		TestSauter wTestSauter = new TestSauter();
		wTestSauter.setCouleur(getCouleur());
		return wTestSauter;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}
}
