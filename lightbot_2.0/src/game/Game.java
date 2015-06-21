package game;

import views.fenetre.Fenetre;
import views.menu.Home;

/**
 * Point d'entrée du programme
 *
 */
public class Game {

	/**
	 * Point d'entré du programme
	 *
	 * @param aArgs
	 */
	public static void main(String[] aArgs) {
		Home wViewHome = new Home(Fenetre.FENETRE);
		wViewHome.runHome();
	}

}
