package models.action;

import models.bot.Bot;
import models.cases.Case;
import models.cases.CaseInterrupteur;
import models.cases.CaseLampe;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Action Allumer
 *
 */
public class Allumer extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "allumer";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		if (!valid(aBot, aCarte)) {
			throw new LightBotException("Cette case ne peut pas etre allumee");
		}
		Case aCase = aCarte.getCase(aBot.getPosition());
		if (aCase instanceof CaseLampe) {
			((CaseLampe) aCase).activate();
		}
		if (aCase instanceof CaseInterrupteur) {
			((CaseInterrupteur) aCase).changeCase(aCarte);
		}
	}

	@Override
	public Action copy() {
		Allumer wAllumer = new Allumer();
		wAllumer.setCouleur(getCouleur());
		return wAllumer;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		Case aCase = aCarte.getCase(aBot.getPosition());
		if (aCase instanceof CaseLampe) {
			return true;
		}
		if (aCase instanceof CaseInterrupteur) {
			return true;
		}
		return false;
	}

}
