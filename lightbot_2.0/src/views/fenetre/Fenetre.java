package views.fenetre;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

import views.View;

/**
 * Fenetre contient la création d'une fenêtre JSFML
 *
 */
public class Fenetre extends View {
	public static RenderWindow FENETRE = new RenderWindow(new VideoMode(1280, 800), "Code Me", Window.CLOSE
			| Window.TITLEBAR);

	/** Fenetre ) utiliser pour l'affichage */
	private RenderWindow pWindow;

	/**
	 *
	 * @param aWindow
	 *            RenderWindow de la fenêtre
	 */
	public Fenetre(RenderWindow aWindow) {
		super(new FloatRect(0, 0, aWindow.getSize().x, aWindow.getSize().y));
		this.pWindow = aWindow;
	}

	/**
	 * Retourne la RenderWindow de la fenêtre
	 *
	 * @return RenderWindow de la fenêtre
	 */
	public RenderWindow getWindow() {
		return this.pWindow;
	}

	@Override
	public void initView() {
	}
}
