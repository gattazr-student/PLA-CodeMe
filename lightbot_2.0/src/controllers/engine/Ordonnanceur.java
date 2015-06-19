package controllers.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import models.action.Action;
import models.action.Avancer;
import models.action.Break;
import models.action.Notify;
import models.action.Route;
import models.basic.Couleur;
import models.basic.Etat;
import models.bot.Bot;
import models.niveau.Niveau;
import exceptions.LightBotException;

/**
 * Ordonanceur permettant l'execution de plusieurs robots en parallele
 *
 * @author gattazr
 *
 */
public class Ordonnanceur {

	/** Nombre de coups effectué depuis le début de l'ordonanceur */
	private int pNbCoups;
	/** Flag permetant d'effectuer le Notify à la fin de la gestion du step */
	private boolean pNotify;
	/** Liste de pile d'itérateur d'Action. Utilisé pour conserver la position des bots dans les fonctions */
	List<Stack<Iterator<Action>>> pStacks;
	/** Niveau sur lequel l'Ordonanceur est lancé */
	private Niveau pNiveau;
	/**
	 * Liste contenant la dernière Action effectuée par chancun des Bots. Utilisée pour les affichages
	 * "Action courante"
	 */
	private List<Action> pPrev;

	/**
	 * Création de l'Ordonanceur pour un niveau donné
	 *
	 * @param aNiveau
	 */
	public Ordonnanceur(Niveau aNiveau) {
		this.pNiveau = aNiveau;
		this.pNotify = false;
		this.pNbCoups = 0;
		this.pStacks = new LinkedList<Stack<Iterator<Action>>>();
		for (Bot wBot : aNiveau.getBots()) {
			Route wMain = wBot.getRouteMain();
			Stack<Iterator<Action>> wStack = new Stack<Iterator<Action>>();
			wStack.push(wMain.iterator());
			this.pStacks.add(wStack);
		}
		/* Création de deux éléments fictifs pour simuler les anciennes dernière actions */
		this.pPrev = new ArrayList<>();
		this.pPrev.add(new Avancer());
		this.pPrev.add(new Avancer());
	}

	/**
	 * Retourne le nombre de coups effectué depuis le début de l'ordonanceur
	 *
	 * @return
	 */
	public int getNbCoups() {
		return this.pNbCoups;
	}

	public Niveau getNiveau() {
		return this.pNiveau;
	}

	/**
	 * Executé une Action pour chacun des bots.
	 *
	 * @return vrai si au moins une action a été effectué. false sinon.
	 * @throws LightBotException
	 *             si une exception a été levé pendant l'exécution de l'Action.
	 */
	public boolean step() throws LightBotException {
		int i = 0;
		boolean wRes = false;
		/* Effectue une action pour chacun des bots */
		for (Stack<Iterator<Action>> wStack : this.pStacks) {
			this.pPrev.get(i).clearBotsCourant();
			Bot wBot = this.pNiveau.getBots().get(i);
			if (stepOne(wStack, wBot, i)) {
				this.pPrev.get(i).addBotCourant(wBot.getName());
				wRes = true;
			}
			i++;
		}

		List<Bot> wBots = this.pNiveau.getBots();
		/* Notify si le flag a été levé */
		if (this.pNotify) {
			for (Bot wBot : wBots) {
				wBot.setEtat(Etat.ACTIF);
			}
		}

		/* Vérifie que les Bots sont tous sur des cases différentes. Retourne une exception sinon */
		int wNbBots = wBots.size();
		Bot wBot1, wBot2;
		for (int wI = 0; wI < wNbBots; wI++) {
			wBot1 = wBots.get(wI);
			for (int wJ = wI + 1; wJ < wNbBots; wJ++) {
				wBot2 = wBots.get(wJ);
				if (wBot1.getPosition().equals(wBot2.getPosition())) {
					throw new LightBotException("Deux bots ne peuvent pas se trouver sur la meme case");
				}
			}
		}
		return wRes;
	}

	/**
	 * Effetue une action pour un bot donnée
	 *
	 * @param aStack
	 *            Pile d'irateur contenant les Actions à effectuer par le Bot
	 * @param aBot
	 *            Bot sur lequel appliquer les Action
	 * @param aIdentif
	 *            numéro identifiant le Bot dans les structure de la classe
	 * @return Vrai une Action a été effectué ou que le Bot est en attente. False sinon
	 * @throws LightBotException
	 */
	private boolean stepOne(Stack<Iterator<Action>> aStack, Bot aBot, int aIdentif) throws LightBotException {

		/* Le bot est en attente d'être réveillé (Wait) */
		if (aBot.getEtat() == Etat.PASSIF) {
			return true;
		}

		/* Recherche du premier élément non null dans la pile */
		while (!aStack.isEmpty() && aStack.peek() == null) {
			aStack.pop();
		}
		/* Retourne faux si la pile est vide */
		if (aStack.isEmpty()) {
			return false;
		}
		/* Récupère l'Iterator sans le retirer de la pile */
		Iterator<Action> wIt = aStack.peek();
		if (wIt == null) {
			/* L'Iterator est null, ce qui est une erreur normalement impossible */
			System.err.println("Unexpected iterator null");
			return false;
		} else {
			/* Vérifie que l'itérator à un élément suivant */
			if (wIt.hasNext()) {
				Action wAction = wIt.next();
				/* Recher la prochaine action à effectuer dans cette liste */
				while ((wAction.getCouleur() == Couleur.ROUGE || wAction.getCouleur() == Couleur.VERT)
						&& wAction.getCouleur() != aBot.getCouleur() && wIt.hasNext()) {
					wAction = wIt.next();
				}
				/* Si la pile contenait une Action executable */
				if (wAction.getCouleur() == Couleur.BLANC || wAction.getCouleur() == aBot.getCouleur()) {
					/* Si il s'agit d'une route, on empile la route et on relancer oneStep */
					if (wAction instanceof Route) {
						aStack.push(((Route) wAction).iterator());
						return stepOne(aStack, aBot, aIdentif);
					} else {
						/* Si il s'agit d'un Break, on dépile et on relance oneStep */
						if (wAction instanceof Break) {
							aStack.pop();
							return stepOne(aStack, aBot, aIdentif);
						}
						/* Si il s'agit de Notify, on met à jour le flag et on retourne vrai */
						if (wAction instanceof Notify) {
							this.pNotify = true;
						} else {
							/*
							 * Si il s'agit d'un action, on signale l'Action en cours, on l'applique et on
							 * retoune vrai
							 */
							this.pPrev.set(aIdentif, wAction);
							wAction.apply(aBot, this.pNiveau.getCarte());
						}
						this.pNbCoups++;
						return true;
					}
				} else {
					/* Si aucune action n'a été trouvé dans cette liste, on dépile et on relancer oneStep */
					aStack.pop();
					return stepOne(aStack, aBot, aIdentif);
				}

			} else {
				/* Si on a déja récupéré la fin de la liste, on dépile et on relancer oneStep */
				aStack.pop();
				return stepOne(aStack, aBot, aIdentif);
			}
		}
	}
}