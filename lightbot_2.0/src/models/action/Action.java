package models.action;

import java.util.LinkedList;
import java.util.List;

import models.ObservableModel;
import models.basic.Couleur;
import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Représentation d'une Action de lightbot
 *
 */
public abstract class Action extends ObservableModel {

	/** Couleur de l'Action */
	private Couleur pCouleur;
	/** Nom de l'Action */
	private String pNameAction;
	/** Liste des noms des Bots effectuant cette Action */
	private List<String> pCourant;

	/**
	 * Constructeur d'une Action sans nom de couleur Blanche
	 */
	public Action() {
		this.pCouleur = Couleur.BLANC;
		this.pNameAction = new String();
		this.pCourant = new LinkedList<String>();
	}

	/**
	 * Ajout d'un Bot dans la liste des Bots effectuant l'Action
	 *
	 * @param aName
	 *            Nom d'un Bot effectuant l'Action
	 */
	public void addBotCourant(String aName) {
		this.pCourant.add(aName);
	}

	/**
	 * Application de l'action sur un {@link Bot} et une {@link Carte} donnée
	 *
	 * @param aBot
	 *            Bot sur lequelle appliquer l'action
	 * @param aCarte
	 *            Carte sur laquelle le bot se trouve
	 * @throws LightBotException
	 *             Retourne une exception si l'application de l'Action des invalide
	 */
	abstract public void apply(Bot aBot, Carte aCarte) throws LightBotException;

	/**
	 * Vide la liste des Bots courant de l'Action
	 */
	public void clearBotsCourant() {
		this.pCourant.clear();
	}

	/**
	 * Retourne une copie de l'Action.
	 *
	 * @return Action copie
	 */
	public abstract Action copy();

	/**
	 * Retourne la liste des Bots courant
	 *
	 * @return Liste des Bots courants
	 */
	public List<String> getBotsCourant() {
		return this.pCourant;
	}

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
