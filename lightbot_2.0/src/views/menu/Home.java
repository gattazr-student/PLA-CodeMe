package views.menu;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

import views.fenetre.Fenetre;
import views.fenetre.Panel;
import views.jsfml.VBouton;

public class Home extends Fenetre {

	private Panel pPanelEntete;
	private Panel pPanelJouer;
	private Panel pPanelTuto;
	private Panel pPanelOptions;

	public Home(RenderWindow aWindow) {
		super(aWindow);
		/* Création des panels */
		float wXSep, wYSep;
		float wYSepBouton;
		Vector2i wSize = aWindow.getSize();

		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = wSize.x; // Calcul 100% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.35); // Calcul 25% de la hauteur de la fenêtre

		wYSepBouton = (float) (wSize.y * 0.20); // Calcul 20% de la hauteur de la fenêtre

		/* Ajoute les 5 panels dans la liste des éléments de la vue */

		this.pPanelEntete = new Panel(new FloatRect(0, 0, wXSep, wYSep));
		this.pPanelJouer = new Panel(new FloatRect(0, wYSep, wXSep, wYSepBouton));
		this.pPanelTuto = new Panel(new FloatRect(0, wYSep + wYSepBouton, wXSep, wYSepBouton));
		this.pPanelOptions = new Panel(new FloatRect(0, wYSep + 2 * wYSepBouton, wXSep, wYSepBouton));

		initView();
	}

	private void initEntete(String aNomBouton, String aNomSprite) {
		float wWidth = 983;
		float wHeight = 350;
		this.pPanelEntete.clearView();
		VBouton wButton_Entete = new VBouton(new FloatRect((this.pPanelEntete.getWidth() / 2 - wWidth / 2),
				(this.pPanelEntete.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelEntete.addView(wButton_Entete);
		addView(this.pPanelEntete);
	}

	private void initJouer(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		this.pPanelJouer.clearView();
		VBouton wButton_Jouer = new VBouton(new FloatRect((this.pPanelJouer.getWidth() / 2 - wWidth / 2),
				(this.pPanelJouer.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelJouer.addView(wButton_Jouer);
		addView(this.pPanelJouer);
	}

	private void initOptions(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		this.pPanelOptions.clearView();
		VBouton wButton_Opt = new VBouton(new FloatRect((this.pPanelOptions.getWidth() / 2 - wWidth / 2),
				(this.pPanelOptions.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelOptions.addView(wButton_Opt);
		addView(this.pPanelOptions);
	}

	private void initTuto(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		this.pPanelTuto.clearView();
		VBouton wButton_Tuto = new VBouton(new FloatRect((this.pPanelTuto.getWidth() / 2 - wWidth / 2),
				(this.pPanelTuto.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelTuto.addView(wButton_Tuto);
		addView(this.pPanelTuto);
	}

	@Override
	public void initView() {
		initEntete("Entete", "res/logo/logo.png");
		initJouer("Jouer", "res/menu/sprite_jouer_n.png");
		initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
		initOptions("Options", "res/menu/sprite_opt_n.png");
	}

	public void optionMenu() {
		// Nettoyage de la fenetre
		clearView();
		getWindow().display();
	}

	public void redraw() {
		RenderWindow wWindow = getWindow();
		wWindow.clear();
		initView();
		draw(wWindow, new RenderStates(new Transform()));
		wWindow.display();
	}

	public void runHome() {
		RenderWindow aWindow = getWindow();
		float wWidth = 302;
		float wHeight = 57;

		redraw();

		/* Limite le framerate */
		aWindow.setFramerateLimit(30);
		while (aWindow.isOpen()) {

			/* Gère les events */
			for (Event wEvent : aWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					aWindow.close();
				}
				if (wEvent.type == Event.Type.RESIZED) {
					redraw();
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					if (wMouseEvent.button == Button.LEFT) {

						Vector2f pos = new Vector2f(wMouseEvent.position);

						if (this.pPanelJouer.contains(pos)
								&& !(pos.x < (this.pPanelJouer.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelJouer.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight())
										&& !(pos.x > (this.pPanelJouer.getWidth() / 2 + wWidth / 2))
										&& !(pos.y > (this.pPanelJouer.getHeight() / 2 + wHeight / 2)
												+ this.pPanelEntete.getHeight())) {
							// Click sur le Bouton Jouer
							selectLevel();
							redraw();

						} else if (this.pPanelTuto.contains(pos)
								&& !(pos.x < (this.pPanelTuto.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelTuto.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())
										&& !(pos.x > (this.pPanelTuto.getWidth() / 2 + wWidth / 2))
										&& !(pos.y > (this.pPanelTuto.getHeight() / 2 + wHeight / 2)
												+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())) {
							// Click sur le Bouton tuto
							tutoMenu();
							redraw();

						} else if (this.pPanelOptions.contains(pos)
								&& !(pos.x < (this.pPanelOptions.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelOptions.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
										+ this.pPanelTuto.getHeight())
										&& !(pos.x > (this.pPanelOptions.getWidth() / 2 + wWidth / 2))
										&& !(pos.y > (this.pPanelOptions.getHeight() / 2 + wHeight / 2)
												+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
												+ this.pPanelTuto.getHeight())) {
							// click sur le bouton Option
							optionMenu();
							redraw();
						}
					}
				}
			}
		}
	}

	public void selectLevel() {
		// Nettoyage de la fenetre
		clearView();
		new SelectLevel(getWindow()).runSelect();
		getWindow().display();
	}

	public void tutoMenu() {
		// Nettoyage de la fenetre
		clearView();
		getWindow().display();
	}
}
