package models.action;

import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;
import models.niveau.CaseInterrupteur;
import models.niveau.CaseLampe;

/**
 * Action Allumer
 */
public class Allumer extends Action {

	final static String pNameAction = "allumer";

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		Case aCase = aCarte.getCase(aBot.getPosition());
		if (aCase instanceof CaseLampe) {
			((CaseLampe) aCase).activate();
		}
		if (aCase instanceof CaseInterrupteur) {
			((CaseInterrupteur) aCase).changeCase(aCarte);
		}
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
