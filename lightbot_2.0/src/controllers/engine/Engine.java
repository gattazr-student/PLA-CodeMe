package controllers.engine;

import models.niveau.Niveau;

import org.jsfml.graphics.Color;

import parserXML.ParserXML;
import views.fenetre.Fenetre;
import views.jsfml.VPopup;
import views.niveau.VNiveau;
import controllers.ControlerNiveau;

/**
 * Utilisé pour lancer et gérer un Niveau
 *
 */
public class Engine {

	/**
	 * Constructeur vide
	 */
	public Engine() {
	}

	/**
	 * Lance un niveau
	 * 
	 * @param aFileName
	 *            Fichier XML contenant le niveau
	 */
	public void startLevel(String aFileName) {
		/* Création du model */
		Niveau wModelNiveau = ParserXML.creatLevelXML(aFileName);
		/* Création de la vue */
		VNiveau wViewNiveau = new VNiveau(Fenetre.FENETRE, wModelNiveau);
		/* Création du contrôleur */
		ControlerNiveau wControlerNiveau = new ControlerNiveau(wModelNiveau, wViewNiveau);

		/* Rajout du controlleur dans la vue */
		wViewNiveau.setController(wControlerNiveau);
		/* Ajout de l'observer */
		wModelNiveau.addObserver(wViewNiveau);

		/* Démarrage du niveau */
		boolean wSuccessed = false;
		do {
			wControlerNiveau.resetLevel();
			wControlerNiveau.run();
			/* Comparaison des records */
			int pNbCoups = wControlerNiveau.getNbCoups();
			int pNbActions = wControlerNiveau.getNbAction();
			if (wModelNiveau.getRecordActions() != 0 && pNbActions <= wModelNiveau.getRecordActions()) {
				wSuccessed = true;
				new VPopup(Fenetre.FENETRE, "Bravo ! Le programme etait tres court !", Color.GREEN).run();
			}
			if (wModelNiveau.getRecordCoups() != 0 && pNbCoups <= wModelNiveau.getRecordCoups()) {
				wSuccessed = true;
				new VPopup(Fenetre.FENETRE, "Bravo ! Le temps d'execution etait tres court", Color.GREEN)
						.run();
			}
			if (!wSuccessed) {
				new VPopup(Fenetre.FENETRE, "Bravo ! Mais vous pouvez ameliorer votre score", Color.GREEN)
						.run();
				System.out.println("Valid");
			}
		} while (!wSuccessed);
	}
}
