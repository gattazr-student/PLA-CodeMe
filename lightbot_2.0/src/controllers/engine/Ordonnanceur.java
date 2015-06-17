package controllers.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import models.action.Action;
import models.action.Break;
import models.action.Route;
import models.basic.Couleur;
import models.basic.Etat;
import models.bot.Bot;
import models.niveau.Niveau;
import exceptions.LightBotException;

public class Ordonnanceur {

	private int pNbCoups = 0;

	List<Stack<Iterator<Action>>> pStacks;
	private Niveau pNiveau;

	public Ordonnanceur(Niveau aNiveau) {
		this.pNiveau = aNiveau;
		this.pStacks = new LinkedList<Stack<Iterator<Action>>>();
		for (Bot wBot : aNiveau.getBots()) {
			Route wMain = wBot.getRouteMain();
			Stack<Iterator<Action>> wStack = new Stack<Iterator<Action>>();
			wStack.push(wMain.iterator());
			this.pStacks.add(wStack);
		}
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
			wRes = wRes | stepOne(wStack, this.pNiveau.getBots().get(i));
			i++;
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
	private boolean stepOne(Stack<Iterator<Action>> aStack, Bot aBot) throws LightBotException {

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
						return stepOne(aStack, aBot);
					} else {
						if (wAction instanceof Break) {
							aStack.pop();
							return stepOne(aStack, aBot);
						} else {
							try {
								wAction.apply(aBot, this.pNiveau.getCarte());
								this.pNbCoups++;
							} catch (LightBotException wException) {
								/* TODO Gérer les Exceptions */
								/*
								 * Arreter l'execution de l'ordonanceur à la fin de ce step
								 */
								System.err.println(wException.getMessage());
							}
							return true;
						}
					}
				} else {
					return true;
				}

			} else {
				aStack.pop();
				return stepOne(aStack, aBot);
			}
		}
		return false;
	}
}
