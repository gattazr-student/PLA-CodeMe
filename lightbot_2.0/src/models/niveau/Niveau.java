package models.niveau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.ObservableModel;
import models.action.Action;
import models.action.Route;
import models.bot.Bot;
import models.cases.Case;
import models.cases.CaseLampe;
import models.cases.CaseLampe.ETAT_LAMPE;

/**
 * Niveau Représente un niveau du jeu. Contenant des informations lus depuis un fichier XML, c'est le contenu
 * de cet Objet qui est modifié pendant l'éxécution d'une Route sur un bot
 *
 */
public class Niveau extends ObservableModel {

	/** Carte du niveau */
	private Carte pCarte;
	/** Liste de Bots présent pour ce Niveau */
	private List<Bot> pBots;
	/** Record du niveau (nombre de coups à battre) */
	private int pRecordCoups;
	private int pRecordActions;

	/** Route partagés pas tous les Bots disponible pour ce Niveau */
	private ArrayList<Route> pRoutes;

	/** Nom du niveau */
	private String nom;

	/** List des actions autorisés dans le niveau */
	private ArrayList<Action> pActions;

	/**
	 * Construction d'un niveau vide.
	 */
	public Niveau() {
		this.pBots = new LinkedList<Bot>();
		this.pRoutes = new ArrayList<Route>();
		this.pActions = new ArrayList<Action>();
	}

	/**
	 * Ajoute une action dans la liste d'action autorisé
	 *
	 * @param aAction
	 *            Action à ajouter dans les Action autorisé
	 */
	public void addAction(Action aAction) {
		this.pActions.add(aAction);
	}

	/**
	 * Ajout d'un bot dans le Niveau
	 *
	 * @param aBot
	 *            Bot à ajouter
	 */
	public void addBot(Bot aBot) {
		this.pBots.add(aBot);
	}

	/**
	 * Ajout d'une Route partagé dans le Niveau
	 *
	 * @param aRoute
	 *            Route partagé à rajouter
	 */
	public void addRoute(Route aRoute) {
		this.pRoutes.add(aRoute);
	}

	/**
	 * Retourne la liste des Actions disponibles pour le niveau
	 *
	 * @return liste des Actions disponibles pour le niveau
	 */
	public ArrayList<Action> getActions() {
		return this.pActions;
	}

	/**
	 * Retourne la liste des Bots présent dans le Niveau
	 *
	 * @return liste des Bots présents dans le Niveau
	 */
	public List<Bot> getBots() {
		return this.pBots;
	}

	/**
	 * Retourne la Carte du Niveau
	 *
	 * @return Carte du Niveau
	 */
	public Carte getCarte() {
		return this.pCarte;
	}

	/**
	 * Recupere le nom du niveau
	 *
	 * @return nom du Niveau
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne le record en nombre d'actions du Niveau.
	 *
	 * @return record en nombre d'actions du Niveau.
	 */
	public int getRecordActions() {
		return this.pRecordActions;
	}

	/**
	 * Retourne le record en nombre de coups du Niveau.
	 *
	 * @return record en nombre de coups du Niveau.
	 */
	public int getRecordCoups() {
		return this.pRecordCoups;
	}

	/**
	 * Retourne la liste des Routes dans le Niveau
	 *
	 * @return List des Routes
	 */
	public ArrayList<Route> getRoutes() {
		return this.pRoutes;
	}

	/**
	 * Retourne true si toutes les cases Lampe dans le Niveau sont allumé et que le niveau est donc terminé.
	 * False sinon
	 *
	 * @return true si le niveau est terminé. False sinon
	 */
	public boolean isFinished() {
		Carte wCarte = getCarte();
		int wMaxX = wCarte.getMaxX();
		int wMaxY = wCarte.getMaxY();
		for (int wX = 0; wX < wMaxX; wX++) {
			for (int wY = 0; wY < wMaxY; wY++) {
				Case wCase = wCarte.getCase(wX, wY);
				if (wCase != null) {
					if (wCase instanceof CaseLampe && ((CaseLampe) wCase).getEtat() == ETAT_LAMPE.ETEINT) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Reset tous les Bots contenus dans le Niveau
	 */
	public void resetBot() {
		for (Bot wBot : this.pBots) {
			wBot.reset();
		}
	}

	/**
	 * Reset la Carte
	 */
	public void resetCarte() {
		this.pCarte.reset();
	}

	/**
	 * Définit la Carte du niveau
	 *
	 * @param aCarte
	 *            Nouvelle Carte à utiliser
	 */
	public void setCarte(Carte aCarte) {
		this.pCarte = aCarte;
	}

	/**
	 * Changement du nom
	 *
	 * @param nom
	 *            nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Définit le record en actions du Niveau
	 *
	 * @param aRecordActions
	 *            Nouveau record à utiliser
	 */
	public void setRecordActions(int aRecordActions) {
		this.pRecordActions = aRecordActions;
	}

	/**
	 * Définit le record en coups du Niveau
	 *
	 * @param aRecordCoups
	 *            Nouveau record à utiliser
	 */
	public void setRecordCoups(int aRecordCoups) {
		this.pRecordCoups = aRecordCoups;
	}

}
