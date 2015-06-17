package controllers.engine;

import models.niveau.Niveau;

import org.jsfml.graphics.Color;

import parserXML.ParserXML;
import views.fenetre.Fenetre;
import views.fenetre.FenetreNiveau;
import views.jsfml.VPopup;
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
		// TODO Améliorer les messages
		if (wModelNiveau.getRecordActions() != 0 && pNbActions <= wModelNiveau.getRecordActions()) {
			new VPopup(Fenetre.FENETRE, "Bravo ! Le programme etait tres court !", Color.GREEN).run();
		}
		if (wModelNiveau.getRecordCoups() != 0 && pNbCoups <= wModelNiveau.getRecordCoups()) {
			new VPopup(Fenetre.FENETRE, "Bravo ! Le temps d'execution etait tres court", Color.GREEN).run();
		}
	}
}
