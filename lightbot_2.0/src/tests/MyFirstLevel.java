package tests;

import models.action.Allumer;
import models.action.Avancer;
import models.action.Route;
import models.action.Sauter;
import models.action.TournerDroite;
import models.action.TournerGauche;
import models.basic.Couleur;
import models.basic.Orientation;
import models.basic.Position;
import models.basic.TypeRoute;
import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.CaseBasique;
import models.niveau.CaseLampe;
import models.niveau.Niveau;
import views.fenetre.Fenetre;
import views.niveau.VNiveau;
import controllers.ControlerNiveau;
import controllers.Ordonnanceur;

public class MyFirstLevel {

	/**
	 * Créataion d'un niveau en statique
	 * 
	 * @return
	 */
	public static Niveau createModel() {
		Niveau wLevel1 = new Niveau();
		wLevel1.addBot(new Bot(new Position(0, 0), Orientation.EST, Couleur.BLANC));
		// wLevel1.addBot(new Bot(new Position(1, 2), Orientation.EST, Couleur.ROUGE));
		// wLevel1.addBot(new Bot(new Position(2, 0), Orientation.EST, Couleur.VERT));
		// wLevel1.addBot(new Bot(new Position(3, 3), Orientation.EST, Couleur.BLANC));

		Bot bBot = new Bot(new Position(1, 1), Orientation.EST, Couleur.BLANC);
		wLevel1.addBot(bBot);
		Route aRoute = new Route();// p1
		TournerDroite td = new TournerDroite();
		Sauter aSauter = new Sauter();
		aRoute.addAction(td);
		aRoute.addAction(aSauter);
		wLevel1.addRoute(aRoute);
		wLevel1.getRoutes().get(0).setType(TypeRoute.P1);
		Route bRoute = new Route();// p2
		Avancer aAvance = new Avancer();
		new Allumer();
		new TournerGauche();
		bRoute.addAction(aAvance);
		bRoute.addAction(aAvance);
		wLevel1.addRoute(bRoute);
		wLevel1.getRoutes().get(1).setType(TypeRoute.P2);
		wLevel1.getBots().get(0).getBot_Route().addAction(aAvance);
		wLevel1.getBots().get(0).getBot_Route().addAction(aAvance);
		wLevel1.getBots().get(0).getBot_Route().addAction(aAvance);
		Carte wCarte = new Carte(4, 4);
		// wCarte.addCase(new CaseBasique(new Position(0, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(0, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(0, 1), 2));
		wCarte.addCase(new CaseLampe(new Position(0, 0), 3));
		// wCarte.addCase(new CaseBasique(new Position(1, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(1, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(1, 1), 2));
		wCarte.addCase(new CaseLampe(new Position(1, 0), 3));
		// wCarte.addCase(new CaseBasique(new Position(2, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(2, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(2, 1), 2));
		CaseLampe wLamp = new CaseLampe(new Position(2, 0), 3);
		wLamp.activate();
		wCarte.addCase(wLamp);
		wCarte.addCase(new CaseBasique(new Position(3, 3), 0));
		wCarte.addCase(new CaseBasique(new Position(3, 2), 1));
		wCarte.addCase(new CaseBasique(new Position(3, 1), 2));
		wCarte.addCase(new CaseLampe(new Position(3, 0), 3));

		wLevel1.setCarte(wCarte);
		return wLevel1;
	}

	public static void main(String[] aArgs) {
		System.out.println("Hello World!");

		/* Création du model */
		Niveau wModelNiveau = createModel();
		/* Création de la vue */
		VNiveau wViewNiveau = new VNiveau(Fenetre.FENETRE, wModelNiveau);
		/* Création du contrôleur */
		ControlerNiveau wControlerNiveau = new ControlerNiveau(wModelNiveau, wViewNiveau);

		/* Rajout du controlleur dans la vue */
		wViewNiveau.setController(wControlerNiveau);
		/* Ajout de l'observer */

		Ordonnanceur o1 = new Ordonnanceur(wModelNiveau);
		Route aBotRoute = new Route();
		Route bBotRoute = new Route();
		aBotRoute = wModelNiveau.getBots().get(0).getBot_Route();
		bBotRoute = wModelNiveau.getBots().get(1).getBot_Route();
		int aBotsize = wModelNiveau.getBots().get(0).getBot_Route().getAction().size();
		int bBotsize = wModelNiveau.getBots().get(1).getBot_Route().getAction().size();
		while (aBotRoute.getPosition() < aBotsize || bBotRoute.getPosition() < bBotsize) {
			o1.step();
		}
		wModelNiveau.addObserver(wViewNiveau);
		wViewNiveau.run();
		System.out.println(wModelNiveau.getBots().get(0).getPosition().getX());
		System.out.println(wModelNiveau.getBots().get(0).getPosition().getY());

		/* Démarrage du niveau */

		System.out.println("Goodbye World!");

	}

}
