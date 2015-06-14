package models.action;

import java.util.ArrayList;

import models.bot.Bot;
import models.niveau.Carte;

/**
 * Ensemble d'Action à effectuer
 *
 */
public class Route extends Action {

	private ArrayList<Action> action;
	private int taille;

	public Route() {
		this.taille = 12;
		this.action = new ArrayList<Action>();
	}

	public Route(int aTaille, ArrayList<Action> aAction) {
		this.taille = aTaille;
		this.action = aAction;
	}

	public void addAction(Action aAction) {
		if (this.action.size() < this.taille) {
			this.action.add(aAction);
		}
	}

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		int asize = this.action.size();
		for (int i = 0; i < asize; i++) {
			this.action.get(i).apply(aBot, aCarte);
		}

	}

	public int get_Taille() {
		return this.taille;
	}

	public ArrayList<Action> getAction() {
		return this.action;
	}

	public void remove_Action(int i) {
		this.action.remove(i);
	}

	public void set_Action(Action aAction, int i) {
		if (this.action.size() < (this.taille - 1)) {
			this.action.set(i, aAction);
		}
	}

	public void set_Taille(int i) {
		this.taille = i;

	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* On peut toujours ��valuer une route */
		int asize = this.action.size();
		int i;
		for (i = 0; i < asize; i++) {
			if (!this.action.get(i).valid(aBot, aCarte)) {
				break;
			}

		}
		if (i < asize - 1) {
			return false;
		} else {
			return true;
		}
	}

}
