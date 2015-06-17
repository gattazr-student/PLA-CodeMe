package models.action;

import models.ObservableModel;
import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Représentation d'une action de lightbot
 *
 */
public abstract class Action extends ObservableModel {

	/**
	 * Couleur de l'Action
	 */
	private Couleur pCouleur;
	private String pNameAction;

	public Action() {
		this.pCouleur = Couleur.BLANC;
	}

	/**
	 * Application de l'action sur un {@link Bot} et une {@link Carte} donnée
	 *
	 * @param aBot
	 *            Bot sur lequelle appliquer l'action
	 * @param aCarte
	 *            Carte sur laquelle le bot se trouve
	 * @throws LightBotException
	 *             TODO
	 */
	abstract public void apply(Bot aBot, Carte aCarte) throws LightBotException;

	/**
	 * Retourne une copie de l'Objet
	 * 
	 * @return
	 */
	public abstract Action copy();

	/**
	 * Retourne la couleur de l'Action.
	 *
	 * @return couleur de l'Action
	 */
	public Couleur getCouleur() {
		return this.pCouleur;
	}

	/**
	 * Retourne le nom de l'action
	 *
	 * @return Nom de l'Action
	 */
	public String getName() {
		return this.pNameAction;
	}

	/**
	 * Définit la couleur de l'Action
	 *
	 * @param aCouleur
	 *            nouvelle Couleur de l'Action
	 */
	public void setCouleur(Couleur aCouleur) {
		this.pCouleur = aCouleur;
	}

	/**
	 * Vérifie si l'Action est valide sur un {@link Bot} et une {@link Carte} donnée
	 *
	 * @param aBot
	 *            Bot sur lequelle appliquer l'action
	 * @param aCarte
	 *            Carte sur laquelle le bot se trouve
	 * @return True si l'action est valide. False sinon
	 */
	abstract public boolean valid(Bot aBot, Carte aCarte);

}
