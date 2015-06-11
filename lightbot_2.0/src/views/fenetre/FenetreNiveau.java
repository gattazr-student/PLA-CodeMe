package views.fenetre;

import models.niveau.Carte;
import models.niveau.Niveau;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import views.View;
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
	private Sprite pInterface;

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
		this.pPanelRoutes = new Panel(new FloatRect(0, wYSep, wXSep, wSize.y - wYSep));
		this.pPanelActions = new Panel(new FloatRect(wXSep, 0, wSize.x - wXSep, wYSep));
		this.pPanelMenu = new Panel(new FloatRect(wXSep, wYSep, wSize.x - wXSep, wSize.y - wYSep));
		addView(this.pPanelCarte);
		addView(this.pPanelRoutes);
		addView(this.pPanelActions);
		addView(this.pPanelMenu);
		initView();
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

	/**
	 * Dessinne l'interface si elle n'a pas déja été dessiné et stocké dans pInterface
	 */
	private void initInterface() {
		if (this.pInterface != null) {
			/* Calcul du vecteur scale à appliquer */
			this.pWindow.draw(this.pInterface);
		}
		/* TODO: Ajouter les boutons Play + retour menu */
	}

	@Override
	public void initView() {
		/* TODO: complete function */
		initInterface();
		initCarte();
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
			for (Event event : this.pWindow.pollEvents()) {
				if (event.type == Event.Type.CLOSED) {
					this.pWindow.close();
				}
				if (event.type == Event.Type.RESIZED) {
					redraw();
				}
				if (event.type == Event.Type.KEY_RELEASED) {
					KeyEvent wSMFLKeyEvent = event.asKeyEvent();
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
