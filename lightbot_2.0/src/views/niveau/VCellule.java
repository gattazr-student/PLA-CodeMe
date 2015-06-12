package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.niveau.Case;
import models.niveau.Cellule;

import org.jsfml.graphics.FloatRect;

import views.View;
import views.bot.VBot;

public class VCellule extends View {

	private Cellule pCellule;
	private List<VCase> pVCases;

	private int pCourant;
	private VBot pVBot;

	public VCellule(Cellule aCellule, VBot aVBot, FloatRect aZone) {
		super(aZone);
		this.pCellule = aCellule;
		this.pVBot = aVBot;
		initView(); // initialise la vue
	}

	@Override
	public void initView() {
		List<Case> wCases = this.pCellule.getCases();
		FloatRect wZone = new FloatRect(0, 0, getWidth(), getHeight());
		this.pCourant = this.pCellule.getCourant();
		this.pVCases = new LinkedList<VCase>();
		for (Case wCase : wCases) {
			this.pVCases.add(VCase.makeVCase(wCase, wZone));
		}
		setBot(this.pVBot);
		updateCourant();
	}

	// Modifie la zone du bot
	public void setBot(VBot aBot) {
		if (aBot != null) {
			this.pVBot = aBot;
			Case wCase = this.pCellule.getCase();
			if (wCase != null) {
				this.pVBot.setZone(new FloatRect(VBot.DEPL_X, wCase.getHauteur() * -VCase.DEPL_HAUTEUR
						+ -VBot.DEPL_Y, this.pVBot.getWidth(), this.pVBot.getHeight()));
			}
		}
	}

	/**
	 * Met a jour la cellule courante
	 */
	public void updateCourant() {
		clearView();
		if (this.pVCases.isEmpty() == false) {
			addView(this.pVCases.get(this.pCourant - 1));
		}
		if (this.pVBot != null) {
			addView(this.pVBot);
		}
	}

}
