package models.niveau;

import java.util.LinkedList;
import java.util.List;

import models.ObservableModel;
import models.bot.Bot;

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
	 * Construction d'un niveau vide.
	 */
	public Niveau() {
		this.pBots = new LinkedList<Bot>();
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
	 * Retourne le record du Niveau.
	 *
	 * @return record du Niveau
	 */
	public int getRecord() {
		return this.pRecord;
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
	 * Définit le record du Niveau
	 *
	 * @param aRecord
	 *            Nouveau record à utiliser
	 */
	public void setRecord(int aRecord) {
		this.pRecord = aRecord;
	}
}
