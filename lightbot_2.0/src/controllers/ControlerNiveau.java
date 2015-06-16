package controllers;

import models.action.Action;
import models.action.Allumer;
import models.action.Avancer;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.niveau.Niveau;

import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.KeyEvent;

import views.fenetre.FenetreNiveau;
import controllers.engine.Ordonnanceur;

public class ControlerNiveau {

	private Niveau pNiveau;
	private FenetreNiveau pVNiveau;
	private Ordonnanceur pOrdonnanceur;

	public ControlerNiveau(Niveau aNiveau, FenetreNiveau aVNiveau) {
		this.pNiveau = aNiveau;
		this.pVNiveau = aVNiveau;
		this.pOrdonnanceur = null;
	}

	/**
	 * Retourne vrai si toutes les cases lampe ont été allumé et que le niveau est terminé
	 *
	 * @return vrai si le niveau est terminé
	 */
	public boolean isFinished() {
		return this.pNiveau.isFinished();
	}

	/**
	 * Fonction temporaire permettant de tester facilement les actions
	 *
	 * @param wSMFLKeyEvent
	 */
	public void keyboardAction(KeyEvent aSMFLKeyEvent) {
		Action wAction = null;
		if (aSMFLKeyEvent.key.compareTo(Key.UP) == 0) {
			wAction = new Avancer();
		} else if (aSMFLKeyEvent.key.compareTo(Key.LEFT) == 0) {
			wAction = new TournerGauche();
		} else if (aSMFLKeyEvent.key.compareTo(Key.RIGHT) == 0) {
			wAction = new TournerDroite();
		} else if (aSMFLKeyEvent.key.compareTo(Key.DOWN) == 0) {
			wAction = new Allumer();
		} else if (aSMFLKeyEvent.key.compareTo(Key.SPACE) == 0) {
			wAction = new Sauter();
		}
		if (wAction != null) {
			if (wAction.valid(this.pNiveau.getBots().get(0), this.pNiveau.getCarte())) {
				wAction.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
			} else {
				System.err.println("Action impossible");
			}
		}
	}

	/**
	 * Remet le niveau à son état initial
	 */
	public void resetLevel() {
		/* TODO: ControllerNiveau.resetLevel */
		/* Crééer la fonction reset dans Niveau */
	}

	public void run() {
		/* Boucle générale du niveau */
		this.pVNiveau.redraw();
		int wI = 0;
		while (!isFinished()) {
			this.pVNiveau.handleEvents();
			/* Exécution de l'ordonanceur */
			if (this.pOrdonnanceur != null) {
				wI++;
				if (wI == 100000) {
					try {
						if (!this.pOrdonnanceur.step()) {
							this.pOrdonnanceur = null;
						}
					} catch (Exception aException) {
						/* TODO: ControllerNiveau.run : gérer exception provenant de l'ordonanceur */
					}
					wI = 0;
				}
			}
		}
	}

	/**
	 * Démarre l'éxécution du programme décrit par les Actions du Niveau et des Bots
	 */
	public void startRunning() {
		this.pOrdonnanceur = new Ordonnanceur(this.pNiveau);
	}
}
