package models.action;

import java.util.ArrayList;
import java.util.Iterator;

import models.bot.Bot;
import models.niveau.Carte;
import exceptions.LightBotException;

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
			notifyObserver("route_add", null);
		}
	}

	@Override
	public void apply(Bot aBot, Carte aCarte) throws LightBotException {
		/* Un Route ne peut pas être appliqué */
		throw new LightBotException("Une route ne peut pas être appliqué");
	}

	public ArrayList<Action> getAction() {
		return this.action;
	}

	@Override
	public String getName() {
		return this.pName;
	}

	public int getTailleMax() {
		return this.taille;
	}

	public Iterator<Action> iterator() {
		return this.action.iterator();
	}

	public void removeAction(int aPosition) {
		if (aPosition < this.taille) {
			this.action.remove(aPosition);
			notifyObserver("route_remove", null);
		}
	}

	public void set_Action(Action aAction, int i) {
		if (this.action.size() < (this.taille - 1)) {
			this.action.set(i, aAction);
		}
	}

	public void setName(String aName) {
		this.pName = aName;
	}

	public void setTailleMax(int i) {
		this.taille = i;
	}

	public int size() {
		return this.action.size();
	}

	@Override
	public boolean valid(Bot aBot, Carte aCarte) {
		return false;
	}
}
