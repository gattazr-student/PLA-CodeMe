package views.niveau;

import java.util.LinkedList;
import java.util.List;

import models.niveau.Case;
import models.niveau.Cellule;

import org.jsfml.graphics.FloatRect;

import views.View;

public class VCellule extends View {

	private Cellule pCellule;
	private List<VCase> pVCases;

	private int pCourant;

	public VCellule(Cellule aCellule, FloatRect aZone) {
		super(aZone);
		this.pCellule = aCellule;
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
		updateCourant();
	}

	public void updateCourant() {
		clearView();
		if (this.pVCases.isEmpty() == false) {
			addView(this.pVCases.get(this.pCourant - 1));
		}
	}

}
