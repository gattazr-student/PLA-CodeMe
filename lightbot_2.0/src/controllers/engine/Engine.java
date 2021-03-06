package controllers.engine;

import models.niveau.Niveau;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;

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

	private RenderWindow pWindow;

	/**
	 * Création d'un objet Engine permettant de gérer l'éxécution d'un Niveau
	 * 
	 * @param aWindow
	 *            Fenêtre à utiliser pour l'Engine
	 */
	public Engine(RenderWindow aWindow) {
		this.pWindow = aWindow;
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
		VNiveau wViewNiveau = new VNiveau(this.pWindow, wModelNiveau);
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
			if (!wControlerNiveau.isExited()) {
				/* Comparaison des records */
				int pNbCoups = wControlerNiveau.getNbCoups();
				int pNbActions = wControlerNiveau.getNbAction();
				if (wModelNiveau.getRecordActions() != 0) {
					if (pNbActions <= wModelNiveau.getRecordActions()) {
						wSuccessed = true;
						new VPopup(Fenetre.FENETRE, "Bravo ! Le programme etait tres court !", Color.GREEN)
								.run();
					} else {
						new VPopup(Fenetre.FENETRE,
								"Bravo ! Mais vous pouvez reduire la taille du programme", Color.GREEN).run();
					}
				}
				if (wModelNiveau.getRecordCoups() != 0) {
					if (pNbCoups <= wModelNiveau.getRecordCoups()) {
						wSuccessed = true;
						new VPopup(Fenetre.FENETRE, "Bravo ! Le temps d'execution etait tres court",
								Color.GREEN).run();
					} else {
						new VPopup(Fenetre.FENETRE, "Bravo ! Mais vous pouvez reduire le temps d'execution",
								Color.GREEN).run();
					}
				}
			} else {
				// Le niveau a été quitté avec le bouton returned
				wSuccessed = true;
			}
		} while (!wSuccessed);
	}
}
