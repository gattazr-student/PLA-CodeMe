package views.fenetre;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

public final class Fenetre {
	public static RenderWindow FENETRE = new RenderWindow(new VideoMode(800, 800), "Code Me", Window.TITLEBAR
			| Window.CLOSE);
}
