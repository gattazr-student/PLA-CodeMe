package models.bot;

import models.ObservableModel;
import models.action.Action;
import models.action.Route;
import models.basic.Couleur;
import models.basic.Etat;
import models.basic.Orientation;
import models.basic.Position;

/**
 * Réprésentation d'un Bot
 *
 */
public class Bot extends ObservableModel {

	/**
	 * Position du bot sur une Carte
	 */
	private Position pPosition;
	/**
	 * Orientation du Bot sur une Carte
	 */
	private Orientation pOrientation;
	/**
	 * Couleur du Bot
	 */
	private Couleur pCouleur;

	private Route pRouteMain;

	private String pName;

	/**
	 * Etat du Bot (Actif ou Passif)
	 */
	private Etat pEtat;
	private Couleur pCouleurSauvegarde;
	private Orientation pOrientationSauvergarde;
	private Position pPositionSauvergarde;

	/**
	 * Constructeur par défault. Placé en 0,0, Orienté vers Nord et de Couleur Blanche
	 */

	public Bot() {
		this.pCouleur = Couleur.BLANC;
		this.pPosition = new Position();
		this.pOrientation = Orientation.NORD;
		this.pRouteMain = new Route();
		this.pEtat = Etat.ACTIF;
	}

	/**
	 * Création d'un bot selon les paramètres données
	 *
	 * @param aPosition
	 *            Position du bot
	 * @param aOrientation
	 *            Orientation du bot
	 * @param aCouleur
	 *            Couleur du bot
	 */
	public Bot(Position aPosition, Orientation aOrientation, Couleur aCouleur, Etat aEtat) {
		this.pPosition = aPosition;
		this.pPositionSauvergarde = this.pPosition;
		this.pOrientation = aOrientation;
		this.pOrientationSauvergarde = this.pOrientation;
		this.pCouleur = aCouleur;
		this.pCouleurSauvegarde = this.pCouleur;

		this.pRouteMain = new Route();
		this.pEtat = aEtat;
	}

	/**
	 * ajoute une action a la liste
	 */
	public void addAction(Action aAction) {
		this.pRouteMain.addAction(aAction);
	}

	/**
	 * Retourne la Couleur du Bot
	 *
	 * @return Couleur du Bot
	 */
	public Couleur getCouleur() {
		return this.pCouleur;
	}

	/**
	 * retoune l'etat du Bot
	 *
	 * @return Etat du Bot
	 */
	public Etat getEtat() {
		return this.pEtat;
	}

	/**
	 * Retourne le nom du Bot
	 */
	public String getName() {
		return this.pName;
	}

	/**
	 * Retourne l'Orientation du Bot
	 *
	 * @return Orientation du Bot
	 */
	public Orientation getOrientation() {
		return this.pOrientation;
	}

	/**
	 * Retourne la Position du Bot
	 *
	 * @return Position du Bot
	 */
	public Position getPosition() {
		return this.pPosition;
	}

	/**
	 * retourne la liste d'actions du bot
	 *
	 * @return bot_Action du Bot
	 */
	public Route getRouteMain() {
		return this.pRouteMain;
	}

	/**
	 * remove une action
	 */
	public void removeAction(int numberAction) {
		this.pRouteMain.removeAction(numberAction);
	}

	public void reset() {
		this.pCouleur = this.pCouleurSauvegarde;
		this.pOrientation = this.pOrientationSauvergarde;
		this.pPosition = this.pPositionSauvergarde;
		this.pEtat = Etat.ACTIF;
		notifyObserver("bot_reset", null);
	}

	/**
	 * Définit la Couleur du Bot
	 *
	 * @param aCouleur
	 *            nouvelle Couleur du Bot
	 */
	public void setCouleur(Couleur aCouleur) {
		this.pCouleur = aCouleur;
		notifyObserver("bot_couleur", null);
	}

	/**
	 * Modifie l'etat du Bot
	 */
	public void setEtat(Etat aEtat) {
		this.pEtat = aEtat;
	}

	/**
	 * Modifie le nom du Bot
	 */
	public void setName(String aName) {
		this.pName = aName;
	}

	/**
	 * Définit l'Orientation du Bot
	 *
	 * @param aOrientation
	 *            nouvelle Orientation du Bot
	 */
	public void setOrientation(Orientation aOrientation) {
		this.pOrientation = aOrientation;
		notifyObserver("bot_orientation", null);
	}

	/**
	 * Définit la Position du Bot
	 *
	 * @param aPosition
	 *            nouvelle Position du Bot
	 */
	public void setPosition(Position aPosition) {
		this.pPosition = aPosition;
		notifyObserver("bot_position", null);
	}

	/**
	 * Définit la route main du Bot
	 *
	 * @param aRouteMain
	 *            nouvelle route main du Bot
	 */
	public void setRouteMain(Route aRouteMain) {
		this.pRouteMain = aRouteMain;
	}
}
