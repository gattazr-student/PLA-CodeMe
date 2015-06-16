package models.action;

import java.util.ArrayList;
import java.util.Iterator;

import models.bot.Bot;
import models.niveau.Carte;

/**
 * Ensemble d'Action à effectuer
 *
 */
public class Route extends Action {

	final static String pNameAction = "route";

	private ArrayList<Action> action;
	private int taille;
	private String pName;

	public Route() {
		this.taille = 12;
		this.action = new ArrayList<Action>();
		setName(new String());
	}

	public Route(int aTaille, ArrayList<Action> aAction, String aName) {
		this.taille = aTaille;
		this.action = aAction;
		setName(aName);
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

	public String getName() {
		return this.pName;
	}

	public Iterator<Action> iterator() {
		return this.action.iterator();
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

	public void setName(String aName) {
		this.pName = aName;
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		/* On peut toujours evaluer une route */
		return true;
	}
}
