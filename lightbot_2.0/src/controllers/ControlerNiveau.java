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

import org.jsfml.graphics.Color;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.KeyEvent;

import views.fenetre.Fenetre;
import views.jsfml.VPopup;
import views.niveau.VNiveau;
import controllers.engine.Ordonnanceur;
import exceptions.LightBotException;

/**
 * Controler utilisé pour un Niveau
 *
 */
public class ControlerNiveau {

	/** Nombre d'action utilisé pour résoudre le niveau */
	private int pNbAction = 0;
	/** Nombre de coups effectués pour résoudre le niveau */
	private int pNbCoups = 0;
	/** Niveau */
	private Niveau pNiveau;
	/** VNniveau */
	private VNiveau pVNiveau;
	/** Ordonnanceur */
	private Ordonnanceur pOrdonnanceur;
	/** Bot courant (le main affiché dans la fenêtre est le sien) */
	private Bot pBotCourant;
	/** Route en cours de modification */
	private Route pRouteCourant;
	/** Booleén signalant l'utilisation du clavier */
	private boolean pKeyPress;
	/** Booléen permettant le retour au menu quand mis à true */
	private boolean pReturned;

	/**
	 * Construit un Controller de Niveau à partir d'un Niveau et d'une vue Niveau.
	 *
	 * @param aNiveau
	 *            Niveau à utiliser
	 * @param aVNiveau
	 *            Vue à utiliser
	 */
	public ControlerNiveau(Niveau aNiveau, VNiveau aVNiveau) {
		this.pNiveau = aNiveau;
		this.pVNiveau = aVNiveau;
		this.pOrdonnanceur = null;
		this.pBotCourant = this.pNiveau.getBots().get(0);
		this.pRouteCourant = this.pBotCourant.getRouteMain();
		this.pKeyPress = false;
		this.pReturned = false;
	}

	/**
	 * Ajout l'action passé en paramètre à la route courante
	 *
	 * @param aAction
	 *            Action à ajouter dans la Route
	 */
	public void addToRouteCourante(Action aAction) {
		this.pRouteCourant.addAction(aAction.copy());
		this.pVNiveau.redraw();
		this.pNbAction++;
	}

	/**
	 * Retourne le nombre d'Actions utilisés pour résoudre le niveau
	 *
	 * @return nombre d'Actions utilisés pour résoudre le niveau
	 */
	public int getNbAction() {
		return this.pNbAction;
	}

	/**
	 * Retourne le nombre de Coups effectués pour résoudre le niveau
	 *
	 * @return nombre de Coups effectués pour résoudre le niveau
	 */
	public int getNbCoups() {
		return this.pNbCoups;
	}

	public boolean isExited() {
		return this.pReturned;
	}

	/**
	 * Retourne vrai si toutes les cases lampe ont été allumé et que le niveau est terminé
	 *
	 * @return vrai si le niveau est terminé
	 */
	public boolean isFinished() {
		return !this.pKeyPress && this.pNiveau.isFinished();
	}

	/**
	 * Fonction permettant de controler le Bot au clavier
	 *
	 * @param aSMFLKeyEvent
	 *            évènement clavier
	 */
	public void keyboardAction(KeyEvent aSMFLKeyEvent) {
		this.pKeyPress = true;
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
			try {
				wAction.apply(this.pBotCourant, this.pNiveau.getCarte());
			} catch (LightBotException wException) {
				new VPopup(Fenetre.FENETRE, wException.getMessage(), Color.RED).run();
			}
			this.pVNiveau.redraw();
		}
	}

	public void quitLevel() {
		this.pReturned = true;
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
		this.pOrdonnanceur = null;
		this.pKeyPress = false;
		this.pNiveau.resetCarte();
		this.pNiveau.resetBot();
		this.pVNiveau.redraw();
	}

	/**
	 * Execution du Niveau
	 */
	public void run() {
		/* Boucle générale du niveau */
		this.pVNiveau.redraw();
		long wTime = System.currentTimeMillis();
		long wCour, wDiff;
		while (!this.pReturned && !isFinished()) {
			wCour = System.currentTimeMillis();
			wDiff = wCour - wTime;
			this.pVNiveau.handleEvents();
			/* Exécution de l'ordonanceur */
			if (this.pOrdonnanceur != null) {
				if (wDiff > 500) {
					try {
						if (!this.pOrdonnanceur.step()) {
							this.pNbCoups = this.pOrdonnanceur.getNbCoups();
							this.pOrdonnanceur = null;
						}
					} catch (LightBotException wException) {
						new VPopup(Fenetre.FENETRE, wException.getMessage(), Color.RED).run();
						this.pOrdonnanceur = null;
					}
					this.pVNiveau.redraw();
					wTime = System.currentTimeMillis();
				}
			}
		}
		if (this.pOrdonnanceur != null) {
			this.pOrdonnanceur.clearPrev();
			this.pNbCoups = this.pOrdonnanceur.getNbCoups();
		}
	}

	/**
	 * Définit le Bot courant. Remplace la route Main affiché par la route Main de ce Bot.
	 *
	 * @param aBot
	 *            nouveau Bot courant
	 */
	public void setBotCourant(Bot aBot) {
		this.pBotCourant = aBot;
		setRouteCourant(aBot.getRouteMain());
		this.pVNiveau.setRouteMain(aBot.getRouteMain());
		this.pVNiveau.redraw();
	}

	/**
	 * Définit la route en cours de modification
	 *
	 * @param aRouteCourant
	 *            nouvelle Route courante
	 */
	public void setRouteCourant(Route aRouteCourant) {
		this.pRouteCourant = aRouteCourant;
		this.pVNiveau.redraw();
	}

	/**
	 * Démarre l'éxécution du programme décrit par les Actions du Niveau et des Bots
	 */
	public void startRunning() {
		resetLevel();
		this.pOrdonnanceur = new Ordonnanceur(this.pNiveau);
	}
}
