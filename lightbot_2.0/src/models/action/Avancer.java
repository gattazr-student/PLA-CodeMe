package models.action;

import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;

/**
 * Action Avancer
 *
 */
public class Avancer extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/*
		 * Effectue l'action Avancer
		 */
		Position wNextPosition;
		wNextPosition = aBot.getPosition().move(aBot.getOrientation());
		aBot.setPosition(wNextPosition);

	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/*
		 * Acion valide si pas de case (segfault) ou si case destination sur même niveau
		 */
		Case wCaseCourant, wCaseDestination;
		Position wPositionCourant, wNextPosition;

		wPositionCourant = aBot.getPosition();
		wNextPosition = wPositionCourant.move(aBot.getOrientation());

		wCaseCourant = aCarte.getCase(wPositionCourant);
		wCaseDestination = aCarte.getCase(wNextPosition);

		/*
		 * Si la case destination est null, le déplacement est interdit
		 */
		if (wCaseDestination == null) {
			return false;
		}

		int wDestinationHauteur = wCaseDestination.getHauteur();
		int wCouranthauteur = wCaseCourant.getHauteur();
		int wDiff = wDestinationHauteur - wCouranthauteur;

		/* La différence doit être de 0 */
		if (wDiff == 0) {
			return true;
		}

		/* La destination et le courant ne sont pas sur le même niveau */
		return false;
	}
}
