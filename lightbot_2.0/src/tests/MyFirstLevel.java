package tests;

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

public class MyFirstLevel {

	/**
	 * Créataion d'un niveau en statique
	 *
	 * @return
	 */
	public static Niveau createModel() {
		Niveau wLevel1 = new Niveau();
		wLevel1.addBot(new Bot(new Position(0, 0), Orientation.EST, Couleur.BLANC));

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

		/* Démarrage du niveau */
		wViewNiveau.run();
		System.out.println("Goodbye World!");
	}

}
