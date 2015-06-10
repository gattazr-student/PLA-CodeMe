package models.bot;

import models.basic.Couleur;
import models.basic.Orientation;
import models.basic.Position;

/**
 * Réprésentation d'un Bot
 *
 */
public class Bot {

	/**
	 * List des actions disponible pour le Bot
	 */
	// private List<Action> pActions;

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

	/**
	 * Constructeur par défault. Placé en 0,0, Orienté vers Nord et de Couleur Blanche
	 */
	public Bot() {
		this.pCouleur = Couleur.BLANC;
		this.pPosition = new Position();
		this.pOrientation = Orientation.NORD;
	}

	/**
	 * Création dun bot selon les paramètres données
	 *
	 * @param aPosition
	 *            Position du bot
	 * @param aOrientation
	 *            Orientation du bot
	 * @param aCouleur
	 *            Couleur du bot
	 */
	public Bot(Position aPosition, Orientation aOrientation, Couleur aCouleur) {
		this.pPosition = aPosition;
		this.pOrientation = aOrientation;
		this.pCouleur = aCouleur;
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
	 * Définit la Couleur du Bot
	 *
	 * @param aCouleur
	 *            nouvelle Couleur du Bot
	 */
	public void setCouleur(Couleur aCouleur) {
		this.pCouleur = aCouleur;
	}

	/**
	 * Définit l'Orientation du Bot
	 *
	 * @param aOrientation
	 *            nouvelle Orientation du Bot
	 */
	public void setOrientation(Orientation aOrientation) {
		this.pOrientation = aOrientation;
	}

	/**
	 * Définit la Position du Bot
	 *
	 * @param aPosition
	 *            nouvelle Position du Bot
	 */
	public void setPosition(Position aPosition) {
		this.pPosition = aPosition;

	}

}
