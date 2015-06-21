package game;

import views.fenetre.Fenetre;
import controllers.engine.Engine;

public class TestLevel {

	public static void main(String[] aArgs) {
		new Engine(Fenetre.FENETRE).startLevel("res/xml/Conditions/Level1.xml");
	}

}
