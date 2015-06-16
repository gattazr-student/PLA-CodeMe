package views.fenetre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.action.Action;
import models.action.Route;
import models.niveau.Carte;
import models.niveau.Niveau;
import mvc.Observer;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;

import views.View;
import views.action.VAction;
import views.action.VRoute;
import views.action.VRouteListe;
import views.jsfml.VBouton;
import views.jsfml.VImage;
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

	private List<VRouteListe> pVRoutesList;

	public FenetreNiveau(RenderWindow aWindow, Niveau aNiveau) {
		this.pWindow = aWindow;
		this.pNiveau = aNiveau;
		/* Création des quatres panels */
		float wXSep, wYSep;
		Vector2i wSize = this.pWindow.getSize();
		setZone(new FloatRect(0, 0, wSize.x, wSize.y));

		wXSep = wSize.x - 4 * VRoute.LARGEUR; // Calcul 70% de la largeur de la fenêtre
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

		VImage wImage_Fond = new VImage(new FloatRect(0, 0, 59, 59), "res/menu/ciel.jpeg");
		this.pPanelCarte.addView(wImage_Fond);

		this.pVRoutesList = new LinkedList<VRouteListe>();

		initView();
	}

	private int findPosition(Vector2f aPosition) {
		int wDeplX = (int) aPosition.x / VRoute.LARGEUR;
		int wDplY = (int) aPosition.y / VRoute.HAUTEUR;
		return wDplY * 4 + wDeplX;
	}

	/**
	 * Retrouve la VRouteListe sur laquelle un click a été effectué
	 *
	 * @param wPosition
	 * @return
	 */
	private VRouteListe findVRoute(Vector2f aPosition) {
		for (VRouteListe wVRouteListe : this.pVRoutesList) {
			if (wVRouteListe.contains(aPosition)) {
				return wVRouteListe;
			}
		}
		/* TODO: Récupérer la route courante sinon -> main */
		return null;
	}

	public void handleEvents() {
		for (Event wEvent : this.pWindow.pollEvents()) {
			if (wEvent.type == Event.Type.CLOSED) {
				this.pWindow.close();
				System.exit(0);
			}
			if (wEvent.type == Event.Type.RESIZED) {
				redraw();
			}
			if (wEvent.type == Event.Type.KEY_RELEASED) {
				KeyEvent wSMFLKeyEvent = wEvent.asKeyEvent();
				this.pControler.keyboardAction(wSMFLKeyEvent);
			}
			if (wEvent.type == Event.Type.MOUSE_BUTTON_PRESSED) {
				MouseButtonEvent wMouseEvent = wEvent.asMouseButtonEvent();
				Vector2f wPosition = new Vector2f(wMouseEvent.position);
				if (wMouseEvent.button == Button.LEFT) {
					View wView = isClickedOn(wPosition);
					if (wView instanceof VRouteListe) {
						/* Change la route courante */
						this.pControler.setRouteCourant(((VRouteListe) wView).getRoute());
					} else if (wView instanceof VAction) {
						if (this.pPanelActions.contains(wPosition)) {
							/* Ajoute une action dans la courante */
							this.pControler.addToRouteCourante(((VAction) wView).getAction());
						} else if (this.pPanelRoutes.contains(wPosition)) {
							/* Retire l'action dans bonne liste */
							wPosition = Vector2f.sub(wPosition, this.pPanelRoutes.getOrigin());
							VRouteListe wVRoute = findVRoute(wPosition);
							wPosition = Vector2f.sub(wPosition, wVRoute.getOrigin());
							int wIndice = findPosition(wPosition);
							this.pControler.removeFromRoute(wVRoute.getRoute(), wIndice);
						}
					}
				}
			}
		}
	}

	/**
	 * Dessine les boutons actions
	 */
	private void initActions() {
		ArrayList<Action> wAvailable = this.pNiveau.getActions();
		ArrayList<Route> wRoutes = this.pNiveau.getRoutes();
		float wY = (this.pPanelActions.getHeight() - VAction.HAUTEUR) / 2;
		float wX = (this.pPanelActions.getWidth() - (wAvailable.size() + wRoutes.size()) * VAction.LARGEUR) / 2;

		for (Action wAction : wAvailable) {
			this.pPanelActions.addView(VAction.makeVAction(wAction, new FloatRect(wX, wY, VAction.LARGEUR,
					VAction.HAUTEUR)));
			wX += VAction.LARGEUR;
		}
		for (Action wAction : wRoutes) {
			this.pPanelActions.addView(VAction.makeVAction(wAction, new FloatRect(wX, wY, VAction.LARGEUR,
					VAction.HAUTEUR)));
			wX += VAction.LARGEUR;
		}
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
		this.pPanelCarte.addView(new VCarte(wCarte, this.pNiveau.getBots(), new FloatRect(wOriginX, wOriginY,
				wLargeur, wHauteur)));
	}

	private void initMenu() {
		VBouton wButton_BackToMenu = new VBouton(new FloatRect(0, 0, 59, 59), "res/menu/Back_To_Menu.png");
		this.pPanelMenu.addView(wButton_BackToMenu);
		VBouton wButton_Play = new VBouton(new FloatRect(28, 0, 59, 59), "res/menu/marche.png");
		this.pPanelMenu.addView(wButton_Play);
		VBouton wButton_Reset = new VBouton(new FloatRect(77, 0, 59, 59), "res/menu/refresh.png");
		this.pPanelMenu.addView(wButton_Reset);
	}

	private void initRoutes() {
		/* TODO: Récupérer le main du robot courant */
		VRouteListe wVRouteMain = new VRouteListe(this.pNiveau.getBots().get(0).getRouteMain(),
				new FloatRect(0, 0, 4 * VRoute.LARGEUR, 3 * VRoute.HAUTEUR));
		this.pVRoutesList.add(wVRouteMain);
		this.pPanelRoutes.addView(wVRouteMain);
		int depl_cadre = 3 * VRoute.HAUTEUR + 10;
		for (Route wRoute : this.pNiveau.getRoutes()) {
			VRouteListe wVRoute = new VRouteListe(wRoute, new FloatRect(0, depl_cadre, 4 * VRoute.LARGEUR,
					2 * VRoute.HAUTEUR));
			this.pVRoutesList.add(wVRoute);
			this.pPanelRoutes.addView(wVRoute);
			depl_cadre = depl_cadre + 2 * VRoute.HAUTEUR + 10;
		}
	}

	@Override
	public void initView() {
		initCarte();
		initActions();
		initMenu();
		initRoutes();
	}

	public void redraw() {
		this.pWindow.clear();
		draw(this.pWindow, new RenderStates(new Transform()));
		this.pWindow.display();
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
