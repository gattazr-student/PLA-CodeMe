package controllers.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import models.action.Action;
import models.action.Route;
import models.basic.Etat;
import models.bot.Bot;
import models.niveau.Niveau;

public class Ordonnanceur {

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

	public Niveau getNiveau() {
		return this.pNiveau;
	}

	/**
	 * @param : none
	 *
	 * @return : true s'il reste des actions a effectuer
	 */
	public boolean step() {
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
	private boolean stepOne(Stack<Iterator<Action>> aStack, Bot aBot) {
		while (aStack.isEmpty() == false && aStack.peek() == null) {
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
				if (wAction instanceof Route) {
					aStack.push(((Route) wAction).iterator());
					return stepOne(aStack, aBot);
				} else {
					// TODO : effectuer l'action sur le bot
					if (aBot.getEtat() == Etat.ACTIF && wAction.valid(aBot, this.pNiveau.getCarte())) {
						if (wAction.getName() != "break") {
							wAction.apply(aBot, this.pNiveau.getCarte());
							return true;
						} else {
							aStack.pop();
							return true;
						}
					}
					if (aBot.getEtat() == Etat.PASSIF) {
						// inserer wAction dans la pile et passer au Bot suivant
						// aStack.push(((Route) wAction).iterator());
						return false;
					} else {
						/* TODO: throw Exception pour gérer les erreurs d'éxecutions */
						return true;
					}

				}
			} else {
				aStack.pop();
				return stepOne(aStack, aBot);
			}
		}
		return false;
	}

}
