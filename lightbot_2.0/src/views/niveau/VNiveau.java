package views.niveau;

import models.bot.Bot;
import models.niveau.Carte;
import models.niveau.Case;
import models.niveau.Niveau;
import mvc.Observer;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.Transform;
import org.jsfml.graphics.Vertex;
import org.jsfml.graphics.VertexArray;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import views.bot.VBot;
import views.fenetre.Panel;
import controllers.ControlerNiveau;

public class VNiveau implements Observer {

	private Niveau pNiveau;

	private RenderWindow pWindow;
	private ControlerNiveau pControler;
	private Sprite pInterface;
	private Panel pPanelCarte;
	private Panel pPanelRoutes;
	private Panel pPanelActions;
	private Panel pPanelLenu;

	public VNiveau(RenderWindow aWindow, Niveau aNiveau) {
		this.pWindow = aWindow;
		this.pNiveau = aNiveau;
		/* Création des quatres panels */
		float wXSep, wYSep;
		Vector2i wSize = this.pWindow.getSize();
		wXSep = (float) (wSize.x * 0.7); // Calcul 70% de la largeur de la fenêtre
		wYSep = (float) (wSize.y * 0.85); // Calcul 85% de la hauteur de la fenêtre
		this.pPanelCarte = new Panel(this.pWindow, new Vector2f(0, 0), wXSep, wYSep);
		this.pPanelRoutes = new Panel(this.pWindow, new Vector2f(0, wYSep), wXSep, wSize.y);
		this.pPanelActions = new Panel(this.pWindow, new Vector2f(wXSep, 0), wSize.x, wYSep);
		this.pPanelLenu = new Panel(this.pWindow, new Vector2f(wXSep, wYSep), wSize.x, wSize.y);
	}

	/**
	 * Dessine la fenetre selon le modèle contenu dans le controller aController.
	 */
	public void draw() {
		/* Dessine l' interface */
		drawInterface();
		/* Dessine la carte et les bots dans le panel pPanelCarte */
		drawCarte();
		/* Dessine les routes du robot courant */
		/* TODO: draw routes */
	}

	/**
	 * Dessine la Carte dans le panel pPanelCarte
	 */
	private void drawCarte() {
		Carte wCarte = this.pNiveau.getCarte();
		Vector2f wOrigin = this.pPanelCarte.getOrigin();
		float wWidth = this.pPanelCarte.getWidth();
		float wHeight = this.pPanelCarte.getHeight();

		/* Calcul à quel offset centrer la carte (sur x) dans la section */
		float wDeplX = (wOrigin.x + wWidth - wCarte.getMaxX() * VCase.DEPL_X * 1 / 2) / 2;
		float wDeplY = (wOrigin.y + wHeight - wCarte.getMaxY() * VCase.DEPL_Y * 1 / 2) / 2;
		Transform wOffset = Transform.translate(new Transform(), new Vector2f(wDeplX, wDeplY));
		this.pPanelCarte.draw(new VCarte(wCarte), new RenderStates(wOffset));
		/* Déssine les bots */
		for (Bot wBot : this.pNiveau.getBots()) {
			Case wContenant = wCarte.getCase(wBot.getPosition());
			if (wContenant != null) {
				this.pPanelCarte.draw(new VBot(wBot, wContenant.getHauteur()), new RenderStates(wOffset));
			}
		}
	}

	/**
	 * Dessinne l'interface si elle n'a pas déja été dessiné et stocké dans pInterface
	 */
	private void drawInterface() {
		if (this.pInterface != null) {
			/* Calcul du vecteur scale à appliquer */
			this.pWindow.draw(this.pInterface);
		}

		/* Création des Séparateurs */
		VertexArray wSeparator;
		Vertex wPoint1, wPoint2;
		float wXSep, wYSep;
		Vector2i wSize = this.pWindow.getSize();

		/* Séparateur 1 */
		wXSep = (float) (wSize.x * 0.7); // Calcul 70% de la largeur de la fenêtre
		wPoint1 = new Vertex(new Vector2f(wXSep, 0), Color.WHITE);
		wPoint2 = new Vertex(new Vector2f(wXSep, wSize.y), Color.WHITE);
		wSeparator = new VertexArray(PrimitiveType.LINES);
		wSeparator.add(wPoint1);
		wSeparator.add(wPoint2);
		this.pWindow.draw(wSeparator);

		/* Séparateur 2 */
		wYSep = (float) (wSize.y * 0.85); // Calcul 85% de la hauteur de la fenêtre
		wPoint1 = new Vertex(new Vector2f(0, wYSep), Color.WHITE);
		wPoint2 = new Vertex(new Vector2f(wSize.x, wYSep), Color.WHITE);
		wSeparator = new VertexArray(PrimitiveType.LINES);
		wSeparator.add(wPoint1);
		wSeparator.add(wPoint2);
		this.pWindow.draw(wSeparator);

		/* TODO: Ajouter les boutons Play + retour menu */

		/* Sauvegarde l'interface pour ne pas avoir à tout redessiner plus tard */
		Texture wTexture = new Texture();
		wTexture.update(this.pWindow);
		this.pInterface = new Sprite(wTexture);
	}

	public void run() {
		this.pWindow.clear();
		draw();
		this.pWindow.display();

		/* Limite le framerate */
		this.pWindow.setFramerateLimit(30);
		while (this.pWindow.isOpen()) {
			/* Gère les events */
			for (Event event : this.pWindow.pollEvents()) {
				if (event.type == Event.Type.CLOSED) {
					this.pWindow.close();
				}
				if (event.type == Event.Type.RESIZED) {
					this.pWindow.clear();
					draw();
					this.pWindow.display();
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
		if (aString.equals("update-all-view")) {
			this.pWindow.clear();
			draw();
			this.pWindow.display();
		}
	}
}
