package tests;

import controllers.engine.Engine;

public class MySecondLevel {

	public static void main(String[] aArgs) {
		System.out.println("Hello World!");

		Engine wEngine = new Engine();
		wEngine.startLevel("res/xml/Conditions/level6.xml");

		System.out.println("Goodbye World!");
	}

}
