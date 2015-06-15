package tests;

import models.action.Allumer;
import models.action.Avancer;
import models.action.Route;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.basic.Couleur;
import models.basic.Orientation;
import models.basic.Position;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.CaseBasique;
import models.niveau.CaseLampe;
import models.niveau.Niveau;
import views.fenetre.Fenetre;
import views.fenetre.FenetreNiveau;
import controllers.ControlerNiveau;
import controllers.Ordonnanceur;

public class MyFirstLevel {

	/**
	 * Création d'un niveau en statique
	 *
	 * @return
	 */
	public static Niveau createModel() {
		Niveau wLevel1 = new Niveau();

		// Creation et initialisation du robot 1
		Bot wBot1 = new Bot(new Position(0, 0), Orientation.NORD, Couleur.BLANC);
		wLevel1.addBot(wBot1);

		// Creation et initialisation du robot 2
		Bot wBot2 = new Bot(new Position(0, 1), Orientation.EST, Couleur.BLANC);
		wLevel1.addBot(wBot2);

		// Creation de la procedure P1
		Route wRoute1 = new Route();
		wRoute1.addAction(new TournerDroite());
		wRoute1.addAction(new Avancer());
		wRoute1.addAction(new Avancer());
		wLevel1.addRoute(wRoute1);

		// Creation de la procedure P2
		Route wRoute2 = new Route();
		wRoute2.addAction(new Allumer());
		wRoute2.addAction(new Avancer());
		wLevel1.addRoute(wRoute2);

		// Creation du main du robot 1
		Route wRouteMain1 = new Route();
		wRouteMain1.addAction(wRoute1);
		wRouteMain1.addAction(wRoute2);
		wBot1.setRouteMain(wRouteMain1);

		// Creation du main du robot 2
		Route wRouteMain2 = new Route();
		wRouteMain2.addAction(wRoute1);
		wRouteMain2.addAction(new TournerGauche());
		wRouteMain2.addAction(new Avancer());
		wBot2.setRouteMain(wRouteMain2);

		// Creation carte
		Carte wCarte = new Carte(4, 4);
		wCarte.addCase(new CaseBasique(new Position(0, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(0, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(0, 1), 1));
		wCarte.addCase(new CaseLampe(new Position(0, 0), 0));
		wCarte.addCase(new CaseBasique(new Position(1, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(1, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(1, 1), 1));
		wCarte.addCase(new CaseLampe(new Position(1, 0), 0));
		wCarte.addCase(new CaseBasique(new Position(2, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(2, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(2, 1), 1));
		CaseLampe wLamp = new CaseLampe(new Position(2, 0), 0);
		wLamp.activate();
		wCarte.addCase(wLamp);
		wCarte.addCase(new CaseBasique(new Position(3, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(3, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(3, 1), 1));
		wCarte.addCase(new CaseLampe(new Position(3, 0), 0));

		wLevel1.setCarte(wCarte);
		return wLevel1;
	}

	public static void main(String[] aArgs) {
		System.out.println("Hello World!");

		/* Création du model */
		Niveau wModelNiveau = createModel();
		/* Création de la vue */
		FenetreNiveau wViewNiveau = new FenetreNiveau(Fenetre.FENETRE, wModelNiveau);
		/* Création du contrôleur */
		ControlerNiveau wControlerNiveau = new ControlerNiveau(wModelNiveau, wViewNiveau);

		/* Rajout du controlleur dans la vue */
		wViewNiveau.setController(wControlerNiveau);
		/* Ajout de l'observer */
		wModelNiveau.addObserver(wViewNiveau);

		wViewNiveau.redraw();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Ordonnanceur o1 = new Ordonnanceur(wModelNiveau);
		while (o1.step()) {
			wViewNiveau.redraw();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Fin ordonanceur");

		/* Démarrage du niveau */
		wViewNiveau.run();
		System.out.println("Goodbye World!");

	}

}
