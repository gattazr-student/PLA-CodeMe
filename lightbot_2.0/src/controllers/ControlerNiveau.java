package controllers;

import models.action.Allumer;
import models.action.Avancer;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.niveau.Niveau;
import views.fenetre.FenetreNiveau;

public class ControlerNiveau {

	private Niveau pNiveau;
	private FenetreNiveau pVNiveau;

	public ControlerNiveau(Niveau aNiveau, FenetreNiveau aVNiveau) {
		this.pNiveau = aNiveau;
		this.pVNiveau = aVNiveau;
	}

	/* TEMP FUNCTION */
	public void allumerCase() {
		this.pNiveau.getBots().get(0);
		Allumer wAllumer = new Allumer();
		if (wAllumer.valid(this.pNiveau.getBots().get(0), this.pNiveau.getCarte())) {
			wAllumer.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
		} else {
			System.err.println("Avencer impossible");
		}
	}

	public void avancerBot() {
		this.pNiveau.getBots().get(0);
		Avancer wAvancer = new Avancer();
		if (wAvancer.valid(this.pNiveau.getBots().get(0), this.pNiveau.getCarte())) {
			wAvancer.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
		} else {
			System.err.println("Avencer impossible");
		}

	}

	public void sauterBot() {
		this.pNiveau.getBots().get(0);
		Sauter wSauter = new Sauter();
		if (wSauter.valid(this.pNiveau.getBots().get(0), this.pNiveau.getCarte())) {
			wSauter.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
		} else {
			System.err.println("Sauter impossible");
		}

	}

	public void tournerDroite() {
		this.pNiveau.getBots().get(0);
		TournerDroite wTournerDroite = new TournerDroite();
		wTournerDroite.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
	}

	public void tournerGauche() {
		this.pNiveau.getBots().get(0);
		TournerGauche wTournerGauche = new TournerGauche();
		wTournerGauche.apply(this.pNiveau.getBots().get(0), this.pNiveau.getCarte());
	}
}
