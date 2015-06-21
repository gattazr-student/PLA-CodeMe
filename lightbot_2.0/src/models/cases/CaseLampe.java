package models.cases;

import models.basic.Position;

/**
 * CaseLampe représente une Case possédant deux Etats. l'Etat Allumé et l'état Eteint. La case passe d'un état
 * à l'autre lorsque l'on effectue l'action Allumer sur cette case
 *
 */
public class CaseLampe extends Case {

	public enum ETAT_LAMPE {
		ALLUMEE, ETEINT
	}

	/**
	 * Etat de la Case. ALLUME ou ETEINT.
	 */
	private ETAT_LAMPE pEtat;

	public CaseLampe(Position aPosition, int aHauteur) {
		super(aPosition, aHauteur);
		this.pEtat = ETAT_LAMPE.ETEINT;
	}

	/**
	 * Action effectué lorsque le Bot activer la CaseLampe. La case change d'ETAT_LAMPE
	 */
	public void activate() {
		/* Change l'état de la lampe */
		if (this.pEtat == ETAT_LAMPE.ETEINT) {
			this.pEtat = ETAT_LAMPE.ALLUMEE;
		} else {
			this.pEtat = ETAT_LAMPE.ETEINT;
		}
		notifyObserver("caseLampe_etat", null);
	}

	/**
	 * Retourne l'état de la CaseLampe
	 *
	 * @return Etat de la CaseLampe
	 */
	public ETAT_LAMPE getEtat() {
		return this.pEtat;
	}
}
