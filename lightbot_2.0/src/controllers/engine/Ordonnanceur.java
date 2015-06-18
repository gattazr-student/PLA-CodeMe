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

public class Ordonnanceur {

	private int pNbCoups = 0;
	private boolean pNotify;

	List<Stack<Iterator<Action>>> pStacks;
	private Niveau pNiveau;
	/* List contenant la dernière Action effectué par chancun des Bots */
	private List<Action> pPrev;

	public Ordonnanceur(Niveau aNiveau) {
		this.pNiveau = aNiveau;
		this.pNotify = false;
		this.pStacks = new LinkedList<Stack<Iterator<Action>>>();
		for (Bot wBot : aNiveau.getBots()) {
			Route wMain = wBot.getRouteMain();
			Stack<Iterator<Action>> wStack = new Stack<Iterator<Action>>();
			wStack.push(wMain.iterator());
			this.pStacks.add(wStack);
		}
		/* Création de deux éléments fictifs pour simuler les anciennes actions */
		this.pPrev = new ArrayList<>();
		this.pPrev.add(new Avancer());
		this.pPrev.add(new Avancer());
	}

	public int getNbCoups() {
		return this.pNbCoups;
	}

	public Niveau getNiveau() {
		return this.pNiveau;
	}

	/**
	 * @param : none
	 *
	 * @return : true s'il reste des actions a effectuer
	 */
	public boolean step() throws LightBotException {
		int i = 0;
		boolean wRes = false;
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
	 *
	 * @param aStack
	 *            : pile d'iterator d'actions
	 * @param aBot
	 *            : robot courant
	 * @return : s'il reste des actions
	 */
	private boolean stepOne(Stack<Iterator<Action>> aStack, Bot aBot, int aIdentif) throws LightBotException {

		if (aBot.getEtat() == Etat.PASSIF) {
			/* Le bot est en attente d'être réveillé */
			return true;
		}

		while (!aStack.isEmpty() && aStack.peek() == null) {
			aStack.pop();
		}
		if (aStack.isEmpty()) {
			return false;
		}
		Iterator<Action> wIt = aStack.peek();
		if (wIt == null) {
			System.err.println("Unexpected iterator null");
		} else {
			if (wIt.hasNext()) {
				Action wAction = wIt.next();

				while ((wAction.getCouleur() == Couleur.ROUGE || wAction.getCouleur() == Couleur.VERT)
						&& wAction.getCouleur() != aBot.getCouleur() && wIt.hasNext()) {
					wAction = wIt.next();
				}
				if (wAction.getCouleur() == Couleur.BLANC || wAction.getCouleur() == aBot.getCouleur()) {
					if (wAction instanceof Route) {
						aStack.push(((Route) wAction).iterator());
						return stepOne(aStack, aBot, aIdentif);
					} else {
						if (wAction instanceof Break) {
							aStack.pop();
							return stepOne(aStack, aBot, aIdentif);
						}
						if (wAction instanceof Notify) {
							this.pNotify = true;
						} else {
							/* Signale l'Action en cours */
							this.pPrev.set(aIdentif, wAction);
							/* Applique l'Action */
							wAction.apply(aBot, this.pNiveau.getCarte());
						}
						this.pNbCoups++;
						return true;
					}
				} else {
					return true;
				}

			} else {
				aStack.pop();
				return stepOne(aStack, aBot, aIdentif);
			}
		}
		return false;
	}
}