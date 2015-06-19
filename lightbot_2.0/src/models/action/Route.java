package models.action;

import java.util.ArrayList;
import java.util.Iterator;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

/**
 * Ensemble d'Action à effectuer
 *
 */
public class Route extends Action {

	/** Nom de l'Action */
	final static String pNameAction = "route";

	/** Liste d'Action dans la Route */
	private ArrayList<Action> action;
	/** Nombre maximum d'Action dans la route */
	private int pTailleMax;
	/** Nom de la Route */
	private String pName;

	/**
	 * Création d'une route sans nom de longueur maximum 12
	 */
	public Route() {
		this.pTailleMax = 12;
		this.action = new ArrayList<Action>();
		setName(new String());
	}

	/**
	 * Création d'une route
	 *
	 * @param aTaille
	 *            Nombre maximum d'aciton contenu dans la Route
	 * @param aAction
	 *            ArrayListe d'Action dans la Route
	 * @param aName
	 *            Nom de la ROute
	 */
	public Route(int aTaille, ArrayList<Action> aAction, String aName) {
		this.pTailleMax = aTaille;
		this.action = aAction;
		setName(aName);
	}

	/**
	 * Ajout d'une Action dans la route. Si la route est déja pleine, aucun effet
	 *
	 * @param aAction
	 *            Action à rajouter dans la Route
	 */
	public void addAction(Action aAction) {
		if (this.action.size() < this.pTailleMax) {
			this.action.add(aAction);
			notifyObserver("route_add", null);
		}
	}

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* Un Route ne peut pas être appliqué */
		throw new LightBotException("Route ne peut pas etre applique");
	}

	@Override
	public Action copy() {
		Route wRoute = new Route();
		wRoute.action = this.action;
		wRoute.pName = this.pName;
		wRoute.pTailleMax = this.pTailleMax;
		wRoute.setCouleur(getCouleur());
		return wRoute;
	}

	/**
	 * Retourne la liste d'Action contenu dans la Route
	 *
	 * @return liste d'Action contenu dans la Route
	 */
	public ArrayList<Action> getAction() {
		return this.action;
	}

	@Override
	public String getName() {
		return this.pName;
	}

	/**
	 * Retourne le nombre maximum d'Action que peut contenir la Route
	 *
	 * @return nombre maximum d'Action que peut contenir la Route
	 */
	public int getTailleMax() {
		return this.pTailleMax;
	}

	/**
	 * Retourne un itérateur sur la liste d'Action contenu dans la Route
	 *
	 * @return Iterator sur la liste d'Action contenu dans la Route
	 */
	public Iterator<Action> iterator() {
		return this.action.iterator();
	}

	/**
	 * Retire l'Action à la position donnée dans la Route
	 *
	 * @param aPosition
	 *            position de l'Action à retirer
	 */
	public void removeAction(int aPosition) {
		if (aPosition < this.pTailleMax) {
			this.action.remove(aPosition);
			notifyObserver("route_remove", null);
		}
	}

	/**
	 * Définir le nom de la Route
	 *
	 * @param aName
	 *            nouveau nom de la Route
	 */
	public void setName(String aName) {
		this.pName = aName;
	}

	/**
	 * Définit la taille maximum de la Route
	 *
	 * @param aTailleMax
	 *            nouvelle taille maximum
	 */
	public void setTailleMax(int aTailleMax) {
		this.pTailleMax = aTailleMax;
	}

	/**
	 * Retourne le nombre d'Action contenu dans la Route
	 *
	 * @return nombre d'Action contenu dans la Route
	 */
	public int size() {
		return this.action.size();
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return false;
	}
}
