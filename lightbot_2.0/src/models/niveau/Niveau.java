package models.niveau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.ObservableModel;
import models.action.Action;
import models.action.Route;
import models.bot.Bot;
import models.niveau.CaseLampe.ETAT_LAMPE;

/**
 * Niveau Représente un niveau du jeu. Contenant des informations lus depuis un fichier XML, c'est le contenu
 * de cet Objet qui est modifié pendant l'éxécution d'une Route sur un bot
 *
 */
public class Niveau extends ObservableModel {

	/**
	 * Carte du niveau
	 */
	private Carte pCarte;
	/**
	 * Liste de Bots présent pour ce Niveau
	 */
	private List<Bot> pBots;
	/**
	 * Record du niveau (nombre de coups à battre)
	 */
	private int pRecord;

	/**
	 * P1,P2....
	 */
	private ArrayList<Route> pRoutes;

	/**
	 * Nom du niveau
	 */
	private String nom;

	/**
	 * List des actions possible dans le niveau
	 */
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
	 * Ajoute une action en fin de la liste d'action
	 */
	public void addAction(Action aAction) {
		(this.pActions).add(aAction);
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
	 * ajout d'un route dans le Niveau
	 */
	public void addRoute(Route aRoute) {
		this.pRoutes.add(aRoute);
	}

	/**
	 * Retourne la liste des Bots présent dans le Niveau
	 *
	 * @return List des Bots présents dans le Niveau
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
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne le record du Niveau.
	 *
	 * @return record du Niveau
	 */
	public int getRecord() {
		return this.pRecord;
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
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Définit le record du Niveau
	 *
	 * @param aRecord
	 *            Nouveau record à utiliser
	 */
	public void setRecord(int aRecord) {
		this.pRecord = aRecord;
	}

}
