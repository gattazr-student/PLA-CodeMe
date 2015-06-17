package models.action;

import models.basic.Couleur;
import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;

public class TestSauter extends Action {

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		Case wCaseCourant, wCaseDestination;
		Position wPositionCourant, wNextPosition;

		wPositionCourant = aBot.getPosition();
		wNextPosition = wPositionCourant.move(aBot.getOrientation());

		wCaseCourant = aCarte.getCase(wPositionCourant);
		wCaseDestination = aCarte.getCase(wNextPosition);

		/*
		 * Si la case destination est null, le robot va tomber mais le déplacement est autorisé
		 */
		if (wCaseDestination == null) {
			aBot.setCouleur(Couleur.ROUGE);
		}

		int wDestinationHauteur = wCaseDestination.getHauteur();
		int wCouranthauteur = wCaseCourant.getHauteur();
		int wDiff = wDestinationHauteur - wCouranthauteur;

		/* La différence doit être de 1 */
		if (Math.abs(wDiff) == 1) {
			aBot.setCouleur(Couleur.VERT);
		}

		/*
		 * La destination et le courant ont des hauteurs trop différentes ou sont sur le même niveau
		 */
		aBot.setCouleur(Couleur.ROUGE);
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return true;
	}
}
