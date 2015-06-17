package views.jsfml;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

import views.View;

public class VPopup extends View {

	private RenderWindow pWindow;
	private String pMessage;
	private VBouton pOk;
	private Color pColor;

	public VPopup(RenderWindow aWindow, String aMessage, Color aColor) {
		this.pMessage = aMessage;
		this.pWindow = aWindow;
		this.pColor = aColor;
		Vector2i wSize = this.pWindow.getSize();
		setZone(new FloatRect(0, 0, wSize.x, wSize.y));
		initView();
	}

	@Override
	public void initView() {
		/* Place le fond blanc */
		VRectangle wRect = new VRectangle(new FloatRect(0, 0, getWidth(), getHeight()));
		wRect.setFillColor(Color.WHITE);
		addView(wRect);

		/* Place le texte */
		VTexte wTexte = new VTexte(new FloatRect(0, 0, 0, 0), this.pMessage, 50);
		wTexte.setColor(this.pColor);
		FloatRect wBounds = wTexte.getLocalBounds();
		float wX = getWidth() / 2 - wBounds.width / 2;
		float wY = getHeight() / 2 - wBounds.height / 2;
		wBounds = new FloatRect(wX, wY, wBounds.top, wBounds.height);
		wTexte.setZone(wBounds);

		addView(wTexte);

		/* Place le bouton OK */
		VBouton wBouton = new VBouton(new FloatRect(getWidth() / 2 - 48, wY + wBounds.height + 50, 96, 54),
				"ok", "res/menu/marche.png");
		this.pOk = wBouton;
		addView(wBouton);
	}

	public void redraw() {
		draw(this.pWindow, new RenderStates(new Transform()));
		this.pWindow.display();
	}

	public void run() {
		redraw();
		while (this.pWindow.isOpen()) {
			for (Event wEvent : this.pWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					this.pWindow.close();
					System.exit(0);
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					Vector2f wPosition = new Vector2f(wMouseEvent.position);
					if (wMouseEvent.button == Button.LEFT) {
						View wView = isClickedOn(wPosition);
						if (wView == this.pOk) {
							return;
						}
					}
				}
			}
		}
	}
}
