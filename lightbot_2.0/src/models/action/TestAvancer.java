package models.action;

import models.basic.Couleur;
import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;

public class TestAvancer extends Action {

	// ATTENTION : rajouter vérifiaction case suivante libre (non occupée par un robot)

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/*
		 * Met le robot en Vert si on peut avancer (dans le terrain, sauf trou)
		 * Sinon le robot devient Rouge
		 */
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
			aBot.setCouleur(Couleur.ROUGE);
		}

		int wDestinationHauteur = wCaseDestination.getHauteur();
		int wCouranthauteur = wCaseCourant.getHauteur();
		int wDiff = wDestinationHauteur - wCouranthauteur;

		/* La différence doit être de 0 */
		if (wDiff == 0) {
			aBot.setCouleur(Couleur.VERT);
		}

		/* La destination et le courant ne sont pas sur le même niveau */
		aBot.setCouleur(Couleur.ROUGE);
		;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}
}
