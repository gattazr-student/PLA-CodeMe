package models.action;

import java.util.ArrayList;

import models.basic.TypeRoute;
import models.bot.Bot;
import models.niveau.Carte;

/**
 * Ensemble d'Action ¨¤ effectuer
 * 
 */
public class Route extends Action {

	private ArrayList<Action> action;
	private int taille;
	private TypeRoute atype;
	private int aposition;

	public Route() {
		this.atype = TypeRoute.MAIN;
		this.taille = 12;
		this.action = new ArrayList<Action>();
		if (this.action.size() != 0) {
			this.aposition = this.action.size() - 1;
		} else {
			this.aposition = 0;
		}
	}

	public Route(int aTaille, ArrayList<Action> aAction, TypeRoute btype) {
		this.taille = aTaille;
		this.action = aAction;
		this.atype = btype;
		if (this.action.size() != 0) {
			this.aposition = this.action.size() - 1;
		} else {
			this.aposition = 0;
		}
	}

	public void addAction(Action aAction) {
		if (this.action.size() < this.taille) {
			this.action.add(aAction);
		}
	}

	@Override
	public void apply(Bot aBot, Carte aCarte) {
		/* TODO make function */
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

	public int getPosition() {
		return this.aposition;
	}

	public TypeRoute getType() {
		return this.atype;
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

	public void setPosition(int bposition) {
		this.aposition = bposition;
	}

	public void setType(TypeRoute btype) {
		this.atype = btype;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* On peut toujours ¨¦valuer une route */
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
