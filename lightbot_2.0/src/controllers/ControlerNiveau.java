package controllers;

import models.action.Action;
import models.action.Allumer;
import models.action.Avancer;
import models.action.Route;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.bot.Bot;
import models.niveau.Niveau;

import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.KeyEvent;

import views.fenetre.FenetreNiveau;
import controllers.engine.Ordonnanceur;

public class ControlerNiveau {

	private int pNbAction = 0;
	private int pNbCoups = 0;

	private Niveau pNiveau;
	private FenetreNiveau pVNiveau;
	private Ordonnanceur pOrdonnanceur;

	private Bot pBotCourant;
	private Route pRouteCourant;

	public ControlerNiveau(Niveau aNiveau, FenetreNiveau aVNiveau) {
		this.pNiveau = aNiveau;
		this.pVNiveau = aVNiveau;
		this.pOrdonnanceur = null;
		this.pBotCourant = this.pNiveau.getBots().get(0);
		this.pRouteCourant = this.pBotCourant.getRouteMain();
	}

	/**
	 * Ajout l'action passé en paramètre à la route courante
	 * 
	 * @param aAction
	 *            Action à ajouter dans la Route
	 */
	public void addToRouteCourante(Action aAction) {
		this.pRouteCourant.addAction(aAction);
		this.pVNiveau.redraw();
		this.pNbAction++;
	}

	public int getNbAction() {
		return this.pNbAction;
	}

	public int getNbCoups() {
		return this.pNbCoups;
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
				this.pVNiveau.redraw();
			} else {
				System.err.println("Action impossible");
			}
		}
	}

	/**
	 * Retire l'Action à la position aPosition de la Route donnée
	 * 
	 * @param aRoute
	 *            Route dans laquelle retirer l'Action
	 * @param aPosition
	 *            Position de l'Action à retirer dans la Route
	 */
	public void removeFromRoute(Route aRoute, int aPosition) {
		if (aPosition < aRoute.size()) {
			aRoute.removeAction(aPosition);
			this.pVNiveau.redraw();
			this.pNbAction--;
		}
	}

	/**
	 * Remet le niveau à son état initial
	 */
	public void resetLevel() {
		/* Crééer la fonction reset dans Niveau */
		this.pNiveau.resetCarte();
		this.pNiveau.resetBot();
		this.pVNiveau.redraw();
		System.out.println("reset ok");
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
				if (wI == 10000) {
					try {
						if (!this.pOrdonnanceur.step()) {
							this.pNbCoups = this.pOrdonnanceur.getNbCoups();
							this.pOrdonnanceur = null;
						}
					} catch (Exception aException) {
						/* TODO: ControllerNiveau.run : gérer exception provenant de l'ordonanceur */
					}
					this.pVNiveau.redraw();
					wI = 0;
				}
			}
		}
		if (this.pOrdonnanceur != null) {
			this.pNbCoups = this.pOrdonnanceur.getNbCoups();
		}

	}

	public void setBotCourant(Bot aBot) {
		this.pBotCourant = aBot;
		setRouteCourant(aBot.getRouteMain());
		this.pVNiveau.setRouteMain(aBot.getRouteMain());
		this.pVNiveau.redraw();
	}

	public void setRouteCourant(Route aRouteCourant) {
		this.pRouteCourant = aRouteCourant;
		this.pVNiveau.redraw();
	}

	/**
	 * Démarre l'éxécution du programme décrit par les Actions du Niveau et des Bots
	 */
	public void startRunning() {
		this.pOrdonnanceur = new Ordonnanceur(this.pNiveau);
	}
}
