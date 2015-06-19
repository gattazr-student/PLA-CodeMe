package models.action;

import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;
import exceptions.LightBotException;

/**
 * Action Sauter
 *
 */
public class Sauter extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "sauter";

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		if (!destinationExists(aBot, aCarte)) {
			throw new LightBotException("Segmentation Fault ! La destination n'existe pas");
		}
		if (!destinationReachable(aBot, aCarte)) {
			throw new LightBotException("Erreur ! Impossible de sauter sur la case");
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
		Sauter wSauter = new Sauter();
		wSauter.setCouleur(getCouleur());
		return wSauter;
	}

	/**
	 * Retourne true si la destination existe. false sinon
	 *
	 * @param aBot
	 *            aBot à avancer
	 * @param aCarte
	 *            Carte sur lequel le Bot se trouve
	 * @return true si la destination existe. false sinon
	 */
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

	/**
	 * Retourn true si la destination est atteignable. false sinon
	 *
	 * @param aBot
	 *            Bot a déplacer
	 * @param aCarte
	 *            Carte sur lequel le Bot se trouve
	 * @return true si la destination est atteignable. false sinon
	 */
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

		/* La différence absolue doit être de 1 */
		if (Math.abs(wDiff) == 1) {
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
