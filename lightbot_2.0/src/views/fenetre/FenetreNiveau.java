package views.fenetre;

import models.niveau.Carte;
import models.niveau.Niveau;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;

import views.View;
import views.jsfml.VBouton;
import views.niveau.VCarte;
import views.niveau.VCase;
import controllers.ControlerNiveau;

public class FenetreNiveau extends View implements Observer {

	private Niveau pNiveau;
	private RenderWindow pWindow;
	private ControlerNiveau pControler;

	private Panel pPanelCarte;
	private Panel pPanelRoutes;
	private Panel pPanelActions;
	private Panel pPanelMenu;

	public FenetreNiveau(RenderWindow aWindow, Niveau aNiveau) {
		this.pWindow = aWindow;
		this.pNiveau = aNiveau;
		/* Création des quatres panels */
		float wXSep, wYSep;
		Vector2i wSize = this.pWindow.getSize();
		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = (float) (wSize.x * 0.7); // Calcul 70% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.85); // Calcul 85% de la hauteur de la fenêtre
		/* Ajoute les 4 panels dans la liste des éléments de la vue */
		this.pPanelCarte = new Panel(new FloatRect(0, 0, wXSep, wYSep));
		this.pPanelActions = new Panel(new FloatRect(0, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelRoutes = new Panel(new FloatRect(wXSep, 0, wSize.x - wXSep, wYSep));
		this.pPanelMenu = new Panel(new FloatRect(wXSep, wYSep, wSize.x - wXSep, wSize.y - wYSep));
		addView(this.pPanelCarte);
		addView(this.pPanelRoutes);
		addView(this.pPanelActions);
		addView(this.pPanelMenu);
		initView();
	}

	/**
	 * Dessinne les boutons actions
	 */
	private void initActions() {
		VBouton wButton = new VBouton(new FloatRect(0, 0, 59, 59), "res/action/allume.png");
		this.pPanelActions.addView(wButton);

	}

	/**
	 * Dessine la Carte dans le panel pPanelCarte
	 */
	private void initCarte() {
		Carte wCarte = this.pNiveau.getCarte();
		/* Calcul pour centrer la carte dans le panel pPanelCarte */
		/* Calculer la zone dans laquel va se trouve la carte */
		float wLargeur = Math.max(wCarte.getMaxX(), wCarte.getMaxY()) * VCase.DIAG_HORIZONTALE;
		float wHauteur = Math.max(wCarte.getMaxX(), wCarte.getMaxY()) * VCase.DIAG_VERTICALE;
		float wOriginX = (this.pPanelCarte.getWidth() - wLargeur) / 2;
		float wOriginY = (this.pPanelCarte.getHeight() - wHauteur) / 2;
		this.pPanelCarte.addView(new VCarte(wCarte, new FloatRect(wOriginX, wOriginY, wLargeur, wHauteur)));
	}

	@Override
	public void initView() {
		/* TODO: complete function */
		initCarte();
		initActions();
	}

	public void redraw() {
		this.pWindow.clear();
		draw(this.pWindow, new RenderStates(new Transform()));
		this.pWindow.display();
	}

	public void run() {
		redraw();

		/* Limite le framerate */
		this.pWindow.setFramerateLimit(30);
		while (this.pWindow.isOpen()) {
			/* Gère les events */
			for (Event wEvent : this.pWindow.pollEvents()) {
				if (wEvent.type == Event.Type.CLOSED) {
					this.pWindow.close();
				}
				if (wEvent.type == Event.Type.RESIZED) {
					redraw();
				}
				if (wEvent.type == Event.Type.KEY_RELEASED) {
					KeyEvent wSMFLKeyEvent = wEvent.asKeyEvent();
					if (wSMFLKeyEvent.key.compareTo(Key.UP) == 0) {
						this.pControler.avancerBot();
					} else if (wSMFLKeyEvent.key.compareTo(Key.LEFT) == 0) {
						this.pControler.tournerGauche();
					} else if (wSMFLKeyEvent.key.compareTo(Key.RIGHT) == 0) {
						this.pControler.tournerDroite();
					} else if (wSMFLKeyEvent.key.compareTo(Key.DOWN) == 0) {
						this.pControler.allumerCase();
					} else if (wSMFLKeyEvent.key.compareTo(Key.SPACE) == 0) {
						this.pControler.sauterBot();
					}
				}
				if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
					MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
					if (wMouseEvent.button == Button.LEFT) {
						View wClicked = isClickedOn(new Vector2f(wMouseEvent.position));
						System.out.println(String.format("Left click on %s", wClicked.getClass()));
					}
				}
			}
		}
	}

	public void setController(ControlerNiveau aControlerNiveau) {
		this.pControler = aControlerNiveau;
	}

	@Override
	public void update(String aString, Object aObjet) {
		/* si l'évènement nécessite de redessiner complètement le niveau */
		if (aString.equals("update")) {
			redraw();
		}
	}
}
