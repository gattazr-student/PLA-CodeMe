package controllers.engine;

import models.niveau.Niveau;
import parserXML.ParserXML;
import views.fenetre.Fenetre;
import views.fenetre.FenetreNiveau;
import controllers.ControlerNiveau;

/**
 * Utilisé pour lancer et gérer un Niveau
 * 
 */
public class Engine {

	public Engine() {
	}

	public void startLevel(String aFileName) {
		/* Création du model */
		Niveau wModelNiveau = ParserXML.creatLevelXML(aFileName);
		/* Création de la vue */
		FenetreNiveau wViewNiveau = new FenetreNiveau(Fenetre.FENETRE, wModelNiveau);
		/* Création du contrôleur */
		ControlerNiveau wControlerNiveau = new ControlerNiveau(wModelNiveau, wViewNiveau);

		/* Rajout du controlleur dans la vue */
		wViewNiveau.setController(wControlerNiveau);
		/* Ajout de l'observer */
		wModelNiveau.addObserver(wViewNiveau);

		/* Démarrage du niveau */
		wControlerNiveau.run();

		/* Comparaison des records */
		int pNbCoups = wControlerNiveau.getNbCoups();
		int pNbActions = wControlerNiveau.getNbAction();
		if (wModelNiveau.getRecordActions() != 0 && pNbActions <= wModelNiveau.getRecordActions()) {
			System.out.println("Ok gros t'as pas utilisé trop d'actions bien ouèje next step ;)");
			// TODO affichage pop up
		} else if (wModelNiveau.getRecordCoups() != 0 && pNbCoups <= wModelNiveau.getRecordCoups()) {
			System.out.println("Bien frér tu t'es pas fait avoir ;) Pas dépassé nombre de coups max");
			// TODO affichage pop up
		}
	}
}
