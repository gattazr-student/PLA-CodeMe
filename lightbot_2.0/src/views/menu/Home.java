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

import views.View;
import views.fenetre.Fenetre;
import views.fenetre.Panel;
import views.jsfml.VBouton;

public class Home extends View {

	public static void main(String[] aArgs) {
		RenderWindow aWindow = new RenderWindow();
		aWindow = Fenetre.FENETRE;
		Home wViewHome = new Home(Fenetre.FENETRE);
		wViewHome.runHome(aWindow);
		System.out.println("Goodbye World!");

	}

	private Panel pPanelEntete;
	private Panel pPanelJouer;
	private Panel pPanelTuto;
	private Panel pPanelOptions;
	private Panel pPanelPied;

	public Home(RenderWindow aWindow) {

		/* Création des panels */
		float wXSep, wYSep;
		float wYSepBouton, wYSepPied;
		Vector2i wSize = aWindow.getSize();

		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = wSize.x; // Calcul 100% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.35); // Calcul 25% de la hauteur de la fenêtre

		wYSepBouton = (float) (wSize.y * 0.20); // Calcul 20% de la hauteur de la fenêtre
		wYSepPied = (float) (wSize.y * 0.05); // Calcul 5% de la hauteur de la fenêtre

		/* Ajoute les 5 panels dans la liste des éléments de la vue */

		this.pPanelEntete = new Panel(new FloatRect(0, 0, wXSep, wYSep));
		this.pPanelJouer = new Panel(new FloatRect(0, wYSep, wXSep, wYSepBouton));
		this.pPanelTuto = new Panel(new FloatRect(0, wYSep + wYSepBouton, wXSep, wYSepBouton));
		this.pPanelOptions = new Panel(new FloatRect(0, wYSep + 2 * wYSepBouton, wXSep, wYSepBouton));
		this.pPanelPied = new Panel(new FloatRect(0, wSize.y - wYSepPied, wXSep, wYSepPied));

		addView(this.pPanelEntete);
		addView(this.pPanelJouer);
		addView(this.pPanelTuto);
		addView(this.pPanelOptions);
		addView(this.pPanelPied);

		initView();
	}

	public void initEntete(String aNomBouton, String aNomSprite) {

		float wWidth = 983;
		float wHeight = 350;
		VBouton wButton_Entete = new VBouton(new FloatRect((this.pPanelEntete.getWidth() / 2 - wWidth / 2),
				(this.pPanelEntete.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelEntete.addView(wButton_Entete);
	}

	public void initJouer(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		VBouton wButton_Jouer = new VBouton(new FloatRect((this.pPanelJouer.getWidth() / 2 - wWidth / 2),
				(this.pPanelJouer.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelJouer.addView(wButton_Jouer);
	}

	public void initOptions(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		VBouton wButton_Opt = new VBouton(new FloatRect((this.pPanelOptions.getWidth() / 2 - wWidth / 2),
				(this.pPanelOptions.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelOptions.addView(wButton_Opt);
	}

	public void initTuto(String aNomBouton, String aNomSprite) {
		float wWidth = 302;
		float wHeight = 57;
		VBouton wButton_Tuto = new VBouton(new FloatRect((this.pPanelTuto.getWidth() / 2 - wWidth / 2),
				(this.pPanelTuto.getHeight() / 2 - wHeight / 2), wWidth, wHeight), aNomBouton, aNomSprite);
		this.pPanelTuto.addView(wButton_Tuto);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		initEntete("Entete", "res/logo/logo.png");
		initJouer("Jouer", "res/menu/sprite_jouer_n.png");
		initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
		initOptions("Options", "res/menu/sprite_opt_n.png");
	}

	public void optionMenu() {

		// Nettoyage de la fenetre
		clearView();

		// Definition de nouveaux panels

		//
	}

	public void redraw(RenderWindow aWindow) {
		aWindow.clear();
		draw(aWindow, new RenderStates(new Transform()));
		aWindow.display();
	}

	public void run_monde() {

	}

	public void runHome(RenderWindow aWindow) {
		float wWidth = 302;
		float wHeight = 57;

		redraw(aWindow);

		/* Limite le framerate */
		aWindow.setFramerateLimit(30);
		while (aWindow.isOpen()) {

			/* Gère les events */
			for (Event wEvent : aWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					aWindow.close();
				}
				if (wEvent.type == Event.Type.RESIZED) {
					redraw(aWindow);
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					if (wMouseEvent.button == Button.LEFT) {
						// View wClicked = isClickedOn(new Vector2f(wMouseEvent.position));
						// System.out.println(String.format("Left click on %s", wClicked.getClass()));

						// TODO : Gerer le lien entre click et ouverture de page
						Vector2f pos = new Vector2f(wMouseEvent.position);

						if (this.pPanelJouer.contains(pos)
								&& !(pos.x < (this.pPanelJouer.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelJouer.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight())
								&& !(pos.x > (this.pPanelJouer.getWidth() / 2 + wWidth / 2))
								&& !(pos.y > (this.pPanelJouer.getHeight() / 2 + wHeight / 2)
										+ this.pPanelEntete.getHeight())) {
							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_c.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
							initOptions("Options", "res/menu/sprite_opt_n.png");
							redraw(aWindow);

							runHome(aWindow);

							// TODO : ouvrir nouvelle fenetre des mondes

						} else if (this.pPanelTuto.contains(pos)
								&& !(pos.x < (this.pPanelTuto.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelTuto.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())
								&& !(pos.x > (this.pPanelTuto.getWidth() / 2 + wWidth / 2))
								&& !(pos.y > (this.pPanelTuto.getHeight() / 2 + wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())) {
							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_n.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_c.png");
							initOptions("Options", "res/menu/sprite_opt_n.png");
							redraw(aWindow);

							runHome(aWindow);

							// TODO : ouvrir fenetre tuto
						} else if (this.pPanelOptions.contains(pos)
								&& !(pos.x < (this.pPanelOptions.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelOptions.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
										+ this.pPanelTuto.getHeight())
								&& !(pos.x > (this.pPanelOptions.getWidth() / 2 + wWidth / 2))
								&& !(pos.y > (this.pPanelOptions.getHeight() / 2 + wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
										+ this.pPanelTuto.getHeight())) {
							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_n.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
							initOptions("Options", "res/menu/sprite_opt_c.png");
							redraw(aWindow);

							runHome(aWindow);

							// TODO : ouvrir options
						} else {
							// do nothing
						}
					}
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_RELEASED) {

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

							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_n.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
							initOptions("Options", "res/menu/sprite_opt_n.png");
							redraw(aWindow);

							selectLevel();

							// Nettoyage de la fenetre pour afficher les mondes
							// TODO : ouvrir nouvelle fenetre des mondes

						} else if (this.pPanelTuto.contains(pos)
								&& !(pos.x < (this.pPanelTuto.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelTuto.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())
								&& !(pos.x > (this.pPanelTuto.getWidth() / 2 + wWidth / 2))
								&& !(pos.y > (this.pPanelTuto.getHeight() / 2 + wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight())) {
							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_n.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
							initOptions("Options", "res/menu/sprite_opt_n.png");
							redraw(aWindow);

							tutoMenu();

							// TODO : ouvrir fenetre tuto
						} else if (this.pPanelOptions.contains(pos)
								&& !(pos.x < (this.pPanelOptions.getWidth() / 2 - wWidth / 2))
								&& !(pos.y < (this.pPanelOptions.getHeight() / 2 - wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
										+ this.pPanelTuto.getHeight())
								&& !(pos.x > (this.pPanelOptions.getWidth() / 2 + wWidth / 2))
								&& !(pos.y > (this.pPanelOptions.getHeight() / 2 + wHeight / 2)
										+ this.pPanelEntete.getHeight() + this.pPanelJouer.getHeight()
										+ this.pPanelTuto.getHeight())) {
							// changement de l'image pour visualiser le clic
							initEntete("Entete", "res/menu/sprite_codeme.png");
							initJouer("Jouer", "res/menu/sprite_jouer_n.png");
							initTuto("Tutoriel", "res/menu/sprite_tuto_n.png");
							initOptions("Options", "res/menu/sprite_opt_n.png");
							redraw(aWindow);

							optionMenu();

						} else {
							// do nothing
						}
					}
				}
			}
		}
	}

	public void selectLevel() {

		// Nettoyage de la fenetre
		clearView();

		// Definition de nouveaux panels

		//
	}

	public void tutoMenu() {

		// Nettoyage de la fenetre
		clearView();

		// Definition de nouveaux panels

		//
	}
}
