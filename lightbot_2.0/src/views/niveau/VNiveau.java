package views.niveau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.action.Action;
import models.action.Route;
import models.basic.Couleur;
import models.cases.CaseInterrupteur;
import models.niveau.Carte;
import models.niveau.Niveau;
import mvc.Observer;

import org.jsfml.graphics.Color;
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
import views.bot.VBot;
import views.cases.VCase;
import views.cases.VCaseInterrupteur;
import views.fenetre.Fenetre;
import views.fenetre.Panel;
import views.jsfml.VBouton;
import views.jsfml.VImage;
import controllers.ControlerNiveau;

/**
 *
 * Représentation graphique d'un Niveau. La représentation d'un niveau utilise une fenêtre complète
 *
 */
public class VNiveau extends Fenetre implements Observer {

	/** Niveau à représenter */
	private Niveau pNiveau;
	/** Controlleur associé au Niveau */
	private ControlerNiveau pControler;

	/** Panel pour la Carte */
	private Panel pPanelCarte;
	/** Panel pour les Routes */
	private Panel pPanelRoutes;
	/** Panel pour les Actions */
	private Panel pPanelActions;
	/** Panel pour le Menu */
	private Panel pPanelMenu;

	/** Route main courant à afficher */
	private Route pRouteMain;
	/** Représentation Liste de la Route en cours de modification */
	private VRouteListe pVRouteListCourrante;
	/** Liste des représentation des Routes du Niveau */
	private List<VRouteListe> pVRoutesList;

	/** Liste des représentations graphiques des Actions disponibles pour ce Niveau */
	private List<VAction> pVActionsDisponibles;

	/**
	 *
	 * @param aWindow
	 *            Fenêtre à utiliser pour le rendu
	 * @param aNiveau
	 *            Niveau à représenter
	 */
	public VNiveau(RenderWindow aWindow, Niveau aNiveau) {
		super(aWindow);
		this.pNiveau = aNiveau;
		/* Création des quatres panels */
		float wXSep, wYSep;
		Vector2i wSize = getWindow().getSize();
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

		/** Image de fond */
		VImage wImage_Fond = new VImage(new FloatRect(0, 0, getWidth(), getHeight()), "res/font/fond1.png");
		this.pPanelCarte.addView(wImage_Fond);

		this.pVActionsDisponibles = new LinkedList<VAction>();

		this.pRouteMain = aNiveau.getBots().get(0).getRouteMain();
		this.pVRouteListCourrante = null;
		this.pVRoutesList = new LinkedList<VRouteListe>();

		initView();
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
		return null;
	}

	/**
	 * Gestion des évènements de la fenêtre
	 */
	public void handleEvents() {
		RenderWindow wWindow = getWindow();
		for (Event wEvent : wWindow.pollEvents()) {
			if (wEvent.type == Event.Type.CLOSED) {
				wWindow.close();
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
					if (wView instanceof VBouton) {
						String wName = ((VBouton) wView).getName();
						switch (wName) {
						case "reset":
							this.pControler.resetLevel();
							break;
						case "back":
							this.pControler.quitLevel();
							break;
						case "play":
							this.pControler.startRunning();
							break;
						case "couleur_blanc":
							setCouleurActions(Couleur.BLANC);
							redraw();
							break;
						case "couleur_vert":
							setCouleurActions(Couleur.VERT);
							redraw();
							break;
						case "couleur_rouge":
							setCouleurActions(Couleur.ROUGE);
							redraw();
							break;
						}
					} else if (wView instanceof VBot) {
						/* Changement du bot courant */
						this.pControler.setBotCourant(((VBot) wView).getBot());
					} else if (wView instanceof VCaseInterrupteur) {
						((CaseInterrupteur) (((VCaseInterrupteur) wView).getCase())).changeCase(this.pNiveau
								.getCarte());
						redraw();
					} else if (wView instanceof VRouteListe) {
						/* Change la route courante */
						this.pVRouteListCourrante.setTitreColor(Color.BLACK);
						this.pVRouteListCourrante = ((VRouteListe) wView);
						this.pVRouteListCourrante.setTitreColor(Color.RED);
						this.pControler.setRouteCourant(this.pVRouteListCourrante.getRoute());
					} else if (wView instanceof VAction) {
						if (this.pPanelActions.contains(wPosition)) {
							/* Ajoute une action dans la courante */
							this.pControler.addToRouteCourante(((VAction) wView).getAction());
						} else if (this.pPanelRoutes.contains(wPosition)) {
							/* Retire l'action dans bonne liste */
							wPosition = Vector2f.sub(wPosition, this.pPanelRoutes.getOrigin());
							VRouteListe wVRoute = findVRoute(wPosition);
							wPosition = Vector2f.sub(wPosition, wVRoute.getOrigin());
							int wIndice = wVRoute.findPosition(wPosition);
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
		VAction wVAction;
		for (Action wAction : wAvailable) {
			wVAction = VAction.makeVAction(wAction, new FloatRect(wX, wY, VAction.LARGEUR, VAction.HAUTEUR));
			this.pPanelActions.addView(wVAction);
			this.pVActionsDisponibles.add(wVAction);
			wX += VAction.LARGEUR;
		}
		for (Action wAction : wRoutes) {
			wVAction = VAction.makeVAction(wAction, new FloatRect(wX, wY, VAction.LARGEUR, VAction.HAUTEUR));
			this.pVActionsDisponibles.add(wVAction);
			this.pPanelActions.addView(wVAction);
			wX += VAction.LARGEUR;
		}
		/* Ajout des boutons pour le changement des couleurs */
		this.pPanelActions.addView(new VBouton(new FloatRect(0, 0, 40, 40), "couleur_blanc",
				"res/action/BLANC.bmp"));
		this.pPanelActions.addView(new VBouton(new FloatRect(40, 0, 40, 40), "couleur_vert",
				"res/action/VERT.bmp"));
		this.pPanelActions.addView(new VBouton(new FloatRect(80, 0, 40, 40), "couleur_rouge",
				"res/action/ROUGE.bmp"));
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

	/**
	 * Initialisation du panel pPanelMenu
	 */
	private void initMenu() {
		float wY = (this.pPanelMenu.getHeight() - 54) / 2;
		float wX = (this.pPanelMenu.getWidth() - 154 - 59) / 2;
		VBouton wButton_BackToMenu = new VBouton(new FloatRect(wX, wY, 54, 53), "back",
				"res/menu/Back_To_Menu.png");
		this.pPanelMenu.addView(wButton_BackToMenu);
		VBouton wButton_Play = new VBouton(new FloatRect(wX + 56, wY, 96, 54), "play", "res/menu/marche.png");
		this.pPanelMenu.addView(wButton_Play);
		VBouton wButton_Reset = new VBouton(new FloatRect(wX + 154, wY, 59, 54), "reset",
				"res/menu/refresh.png");
		this.pPanelMenu.addView(wButton_Reset);
	}

	/**
	 * Initialisation du panel pPanelRoutes
	 */
	private void initRoutes() {
		int wLargeurRoute = 4 * VRoute.LARGEUR;
		int depl_cadre = 3 * VRoute.HAUTEUR + VRouteListe.OFFSET;
		VRouteListe wVRouteMain = new VRouteListe(this.pRouteMain, new FloatRect(0, 0, wLargeurRoute,
				depl_cadre));
		this.pVRoutesList.clear();
		this.pVRouteListCourrante = wVRouteMain;
		this.pVRouteListCourrante.setTitreColor(Color.RED);
		this.pVRoutesList.add(wVRouteMain);
		this.pPanelRoutes.addView(wVRouteMain);
		depl_cadre = depl_cadre + 15;
		int wHauteurRoute = 2 * VRoute.HAUTEUR + VRouteListe.OFFSET;
		for (Route wRoute : this.pNiveau.getRoutes()) {
			VRouteListe wVRoute = new VRouteListe(wRoute, new FloatRect(0, depl_cadre, wLargeurRoute,
					wHauteurRoute));
			this.pVRoutesList.add(wVRoute);
			this.pPanelRoutes.addView(wVRoute);
			depl_cadre = depl_cadre + wHauteurRoute + 10;
		}
	}

	@Override
	public void initView() {
		initCarte();
		initActions();
		initMenu();
		initRoutes();
	}

	/**
	 * Dessine la fenêtre
	 */
	public void redraw() {
		RenderWindow wWindow = getWindow();
		wWindow.clear();
		draw(wWindow, new RenderStates(new Transform()));
		wWindow.display();
	}

	/**
	 * Définit le controlleur du Niveau
	 * 
	 * @param aControlerNiveau
	 *            Nouveau controller du niveau
	 */
	public void setController(ControlerNiveau aControlerNiveau) {
		this.pControler = aControlerNiveau;
	}

	/**
	 * Définit la couleur des actions disponibles.
	 *
	 * @param aCouleur
	 *            nouvelle couleur à appliquer
	 */
	private void setCouleurActions(Couleur aCouleur) {
		for (VAction VAction : this.pVActionsDisponibles) {
			VAction.setCouleur(aCouleur);
		}
	}

	/**
	 * Définit la Route main courante
	 *
	 * @param aRouteMain
	 *            nouvelle Route main courantes
	 */
	public void setRouteMain(Route aRouteMain) {
		this.pVRouteListCourrante.setTitreColor(Color.BLACK);
		this.pRouteMain = aRouteMain;
		int wLargeurRoute = 4 * VRoute.LARGEUR;
		int depl_cadre = 3 * VRoute.HAUTEUR + VRouteListe.OFFSET;
		VRouteListe wVRouteMain = new VRouteListe(aRouteMain, new FloatRect(0, 0, wLargeurRoute, depl_cadre));

		this.pVRouteListCourrante = wVRouteMain;
		wVRouteMain.setTitreColor(Color.RED);
		if (!this.pVRoutesList.isEmpty()) {
			this.pVRoutesList.remove(0);
			this.pVRoutesList.add(0, wVRouteMain);
		}
		this.pPanelRoutes.clearView();
		for (VRouteListe wVRoute : this.pVRoutesList) {
			this.pPanelRoutes.addView(wVRoute);
		}

	}

	@Override
	public void update(String aString, Object aObjet) {
		/* si l'évènement nécessite de redessiner complètement le niveau */
		if (aString.equals("update")) {
			redraw();
		}
	}
}
