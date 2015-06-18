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
import views.fenetre.Panel;

public class VPopup extends View {

	private RenderWindow pWindow;
	private String pMessage;
	private VBouton pOk;
	private Color pColor;
	private int pSize;

	public VPopup(RenderWindow aWindow, String aMessage, Color aColor) {
		this.pMessage = aMessage;
		this.pWindow = aWindow;
		this.pColor = aColor;
		this.pSize = 50;
		Vector2i wSize = this.pWindow.getSize();
		setZone(new FloatRect(0, 0, wSize.x, wSize.y));
		initView();
	}

	@Override
	public void initView() {
		/* Offset dans la panel */
		float wOffset = 25;
		/* Creation de l'Objet VTexte */
		VTexte wTexte = new VTexte(new FloatRect(wOffset, wOffset, 0, 0), this.pMessage, this.pSize);
		wTexte.setColor(this.pColor);
		FloatRect wBounds = wTexte.getLocalBounds();

		float wHeightBouton = 40;
		float wWidthBouton = 40;
		float wOffsetBouton = 25;
		float wPosXBouton = getOrigin().x + wBounds.width / 2 - wWidthBouton / 2;
		float wPosYBouton = getOrigin().y + wBounds.height + this.pSize / 2 + wOffset + wOffsetBouton;
		VBouton wBouton = new VBouton(new FloatRect(wPosXBouton, wPosYBouton, wWidthBouton, wHeightBouton),
				"ok", "res/action/ok.bmp");
		this.pOk = wBouton;
		//
		/* Creation du Cadre */
		VRectangle wRect = new VRectangle(new FloatRect(0, 0, wBounds.width + 4 * wOffset, wBounds.height
				+ wHeightBouton + this.pSize / 2 + 2 * wOffset + wOffsetBouton));

		// wTexte.setZone(new FloatRect(wOffset, wOffset, wBounds.width, wBounds.height));

		/* Création d'un Panel */
		Panel wPanel = new Panel(new FloatRect(getWidth() / 2 - wRect.getWidth() / 2, getHeight() / 2
				- wRect.getHeight() / 2, wRect.getWidth(), wRect.getHeight()));
		addView(wPanel);
		wPanel.addView(wRect);
		wPanel.addView(wTexte);
		wPanel.addView(wBouton);
	}

	public void redraw() {
		draw(this.pWindow, new RenderStates(new Transform()));
		this.pWindow.display();
	}

	public void run() {
		redraw();
		while (this.pWindow.isOpen()) {
			for (Event wEvent : this.pWindow.pollEvents()) {
				if (wEvent.type == Event.Type.RESIZED) {
					this.pWindow.clear();
					redraw();
				}
				if (wEvent.type == Event.Type.CLOSED) {
					this.pWindow.close();
					System.exit(0);
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					Vector2f wPosition = new Vector2f(wMouseEvent.position);
					System.out.println(wPosition.x + "-" + wPosition.y);
					if (wMouseEvent.button == Button.LEFT) {
						View wView = isClickedOn(wPosition);
						System.out.println(wView);
						if (wView == this.pOk) {
							return;
						}
					}
				}
			}
		}
	}
}
