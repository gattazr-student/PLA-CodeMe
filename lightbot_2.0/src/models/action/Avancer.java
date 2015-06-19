package models.action;

import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;
import exceptions.LightBotException;

/**
 * Action Avancer
 *
 */
public class Avancer extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "avancer";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		if (!destinationExists(aBot, aCarte)) {
			throw new LightBotException("Segmentation Fault ! La destination n'existe pas");
		}
		if (!destinationReachable(aBot, aCarte)) {
			throw new LightBotException("Erreur ! Impossible d'avancer sur la case");
		}
		/*
		 * Effectue l'action Avancer
		 */
		Position wNextPosition;
		wNextPosition = aBot.getPosition().move(aBot.getOrientation());
		aBot.setPosition(wNextPosition);

	}

	@Override
	public Action copy() {
		Avancer wAvancer = new Avancer();
		wAvancer.setCouleur(getCouleur());
		return wAvancer;
	}

	private boolean destinationExists(Bot aBot, Carte aCarte) {
		/*
		 * Acion valide si pas de case (segfault) ou si case destination sur même niveau
		 */
		Case wCaseDestination;
		Position wPositionCourant, wNextPosition;

		wPositionCourant = aBot.getPosition();
		wNextPosition = wPositionCourant.move(aBot.getOrientation());

		wCaseDestination = aCarte.getCase(wNextPosition);

		/*
		 * Si la case destination est null, le déplacement est interdit
		 */
		if (wCaseDestination == null) {
			return false;
		}
		return true;
	}

	private boolean destinationReachable(Bot aBot, Carte aCarte) {
		Case wCaseDestination;
		Position wPositionCourant, wNextPosition;

		wPositionCourant = aBot.getPosition();
		wNextPosition = wPositionCourant.move(aBot.getOrientation());

		Case wCaseCourant = aCarte.getCase(wPositionCourant);
		wCaseDestination = aCarte.getCase(wNextPosition);

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

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return destinationExists(aBot, aCarte) && destinationReachable(aBot, aCarte);
	}
}
